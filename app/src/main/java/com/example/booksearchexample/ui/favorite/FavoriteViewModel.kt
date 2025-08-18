package com.example.booksearchexample.ui.favorite

import androidx.lifecycle.viewModelScope
import com.example.booksearchexample.base.BaseViewModel
import com.example.booksearchexample.base.LoadState
import com.example.booksearchexample.mapper.BookUiMapper
import com.example.booksearchexample.mapper.DomainMapper
import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.ui.favorite.contract.FavoriteUiEvent
import com.example.booksearchexample.ui.favorite.contract.FavoriteUiState
import com.example.booksearchexample.util.FavoriteFilterType
import com.example.booksearchexample.util.FavoriteSortType
import com.example.booksearchexample.util.SEARCH_TIME_DELAY
import com.example.domain.usecase.GetFavoriteBookListUseCase
import com.example.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteBookListUseCase: GetFavoriteBookListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val bookUiMapper: BookUiMapper,
    private val domainMapper: DomainMapper
) : BaseViewModel<FavoriteUiState, FavoriteUiEvent, Nothing> () {
    override fun createInitialState(): FavoriteUiState =FavoriteUiState()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private val searchResult =
        state.map { it.searchRequest }
            .distinctUntilChanged()
            .debounce(SEARCH_TIME_DELAY)
            .flatMapLatest { request ->
                getFavoriteBookListUseCase(
                    keyword = request.inputKeyword,
                    sortType = domainMapper.mapToSortType(request.sortType),
                    start = request.filterType.min,
                    end = request.filterType.max
                )
            }.catch {
                handleException(it)
            }

    init {
        getFavoriteList()
    }

    private fun getFavoriteList() {
        viewModelScope.launch(Dispatchers.IO) {
            searchResult.collect { result ->
                setState {
                    copy(
                        loadState = LoadState.Success,
                        bookList = result.map { book ->
                            bookUiMapper.mapToBookUiModel(book)
                        }
                    )
                }
            }
        }
    }

    private fun inputKeyword(keyword: String) {
        setState {
            copy(
                loadState = LoadState.Idle,
                bookList = listOf(),
                searchRequest = searchRequest.copy(
                    inputKeyword = keyword
                )
            )
        }
    }

    private fun handleException(throwable: Throwable) {
        setState {
            copy(
                loadState = LoadState.Error(throwable)
            )
        }
    }

    private fun clickFavorite(bookUiModel: BookUiModel) {
        val updatedFavorite = !bookUiModel.isFavorite

        viewModelScope.launch(Dispatchers.IO) {
            toggleFavoriteUseCase(
                isFavorite = updatedFavorite,
                book = bookUiMapper.mapToBookEntity(bookUiModel)
            )
        }
    }

    private fun clickSortType(sortType: FavoriteSortType) {
        setState {
            copy(
                loadState = LoadState.Idle,
                bookList = listOf(),
                searchRequest = searchRequest.copy(
                    sortType = sortType
                )
            )
        }
    }

    private fun clickFilterType(filterType: FavoriteFilterType) {
        setState {
            copy(
                loadState = LoadState.Idle,
                bookList = listOf(),
                searchRequest = searchRequest.copy(
                    filterType = filterType
                )
            )
        }
    }

    override fun onEvent(event: FavoriteUiEvent) {
        when (event) {
            is FavoriteUiEvent.InputKeyword -> {
                inputKeyword(event.keyword)
            }

            is FavoriteUiEvent.ClickFavorite -> {
                clickFavorite(event.bookUiModel)
            }

            is FavoriteUiEvent.ClickSortType -> {
                clickSortType(event.sortType)
            }

            is FavoriteUiEvent.ClickFilterType -> {
                clickFilterType(event.filterType)
            }
        }
    }
}