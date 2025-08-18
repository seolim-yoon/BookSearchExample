package com.example.booksearchexample.ui.search.contract

import com.example.booksearchexample.base.LoadState
import com.example.booksearchexample.base.UiState
import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.util.DEFAULT_KEYWORD
import com.example.booksearchexample.util.DEFAULT_PAGE
import com.example.booksearchexample.util.SearchSortType

data class SearchUiState(
    override val loadState: LoadState = LoadState.Success,
    val bookList: List<BookUiModel> = emptyList(),
    val searchRequest: SearchRequest = SearchRequest()
) : UiState

data class SearchRequest(
    val currentPage: Int = DEFAULT_PAGE,
    val inputKeyword: String = DEFAULT_KEYWORD,
    val sortType: SearchSortType = SearchSortType.ACCURACY
)