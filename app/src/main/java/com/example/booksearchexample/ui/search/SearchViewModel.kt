package com.example.booksearchexample.ui.search

import androidx.lifecycle.viewModelScope
import com.example.booksearchexample.base.BaseViewModel
import com.example.booksearchexample.base.LoadState
import com.example.booksearchexample.mapper.BookUiMapper
import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.ui.search.contract.SearchUiEvent
import com.example.booksearchexample.ui.search.contract.SearchUiState
import com.example.booksearchexample.util.DEFAULT_KEYWORD
import com.example.booksearchexample.util.DEFAULT_PAGE
import com.example.booksearchexample.util.ERROR_PAGE
import com.example.booksearchexample.util.PAGE_SIZE
import com.example.booksearchexample.util.SEARCH_TIME_DELAY
import com.example.booksearchexample.util.SearchSortType
import com.example.domain.usecase.GetFavoriteBookListUseCase
import com.example.domain.usecase.SearchBookUseCase
import com.example.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchBookUseCase: SearchBookUseCase,
    private val getFavoriteBookListUseCase: GetFavoriteBookListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val bookUiMapper: BookUiMapper
) : BaseViewModel<SearchUiState, SearchUiEvent, Nothing>() {
    override fun createInitialState(): SearchUiState = SearchUiState()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private val searchResult =
        state.map { it.searchRequest }
            .distinctUntilChanged()
            .debounce(SEARCH_TIME_DELAY)
            .filter { it.inputKeyword.isNotBlank() && it.currentPage != ERROR_PAGE }
            .flatMapLatest { request ->
                flow {
                    emit(
                        searchBookUseCase(
                            keyword = request.inputKeyword,
                            page = request.currentPage,
                            pageSize = PAGE_SIZE,
                            sort = request.sortType.name.lowercase()
                        )
                    )
                }.catch {
                    handleException(it)
                }
            }

    init {
        searchBookByKeyword()
        getFavoriteList()
    }

    private fun searchBookByKeyword() {
        viewModelScope.launch {
            searchResult.collect { result ->
                setState {
                    copy(
                        loadState = if (result.isEnd) LoadState.Complete else LoadState.Success,
                        bookList = bookList.toMutableList().apply {
                            addAll(
                                bookUiMapper.mapToBookUiModelList(
                                    bookListEntity = result,
                                    currentPage = currentState.searchRequest.currentPage
                                )
                            )
                        }
                    )
                }
            }
        }
    }

    private fun loadMore() {
        if (currentState.loadState !is LoadState.Success) return

        setState {
            copy(
                loadState = LoadState.Loading,
                searchRequest = searchRequest.copy(
                    currentPage = (searchRequest.currentPage + 1)
                )
            )
        }
    }

    private fun refresh() {
        setState {
            copy(
                loadState = LoadState.Idle,
                bookList = listOf(),
                searchRequest = searchRequest.copy(
                    currentPage = DEFAULT_PAGE
                )
            )
        }
    }

    private fun inputSearchKeyword(keyword: String) {
        setState {
            copy(
                loadState = LoadState.Idle,
                bookList = listOf(),
                searchRequest = searchRequest.copy(
                    inputKeyword = keyword,
                    currentPage = DEFAULT_PAGE
                )
            )
        }
    }

    private fun handleException(throwable: Throwable) {
        setState {
            copy(
                loadState = LoadState.Error(throwable),
                searchRequest = searchRequest.copy(
                    currentPage = ERROR_PAGE
                )
            )
        }
    }

    private fun getFavoriteList() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteBookListUseCase(DEFAULT_KEYWORD).collect { result ->
                val favoriteIds = result.map { it.id }
                setState {
                    copy(
                        bookList = currentState.bookList.map { book ->
                            book.copy(isFavorite = favoriteIds.contains(book.id))
                        }
                    )
                }
            }
        }
    }

    private fun clickFavorite(bookUiModel: BookUiModel) {
        val updatedFavorite = !bookUiModel.isFavorite

        viewModelScope.launch(Dispatchers.IO) {
            val isSuccess = toggleFavoriteUseCase(
                isFavorite = updatedFavorite,
                book = bookUiMapper.mapToBookEntity(bookUiModel)
            )

            if (isSuccess) {
                setState {
                    copy(
                        bookList = bookList.map { book ->
                            if (book.id == bookUiModel.id) {
                                book.copy(isFavorite = updatedFavorite)
                            } else book
                        }
                    )
                }
            }
        }
    }

    private fun clickSortType(sortType: SearchSortType) {
        setState {
            copy(
                loadState = LoadState.Idle,
                bookList = listOf(),
                searchRequest = searchRequest.copy(
                    sortType = sortType,
                    currentPage = DEFAULT_PAGE
                )
            )
        }
    }

    override fun onEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.LoadMore -> {
                loadMore()
            }

            is SearchUiEvent.Refresh -> {
                refresh()
            }

            is SearchUiEvent.InputKeyword -> {
                inputSearchKeyword(event.keyword)
            }

            is SearchUiEvent.ClickFavorite -> {
                clickFavorite(event.bookUiModel)
            }

            is SearchUiEvent.ClickSortType -> {
                clickSortType(event.sortType)
            }
        }
    }
}