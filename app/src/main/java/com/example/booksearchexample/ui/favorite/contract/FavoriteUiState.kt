package com.example.booksearchexample.ui.favorite.contract

import com.example.booksearchexample.base.LoadState
import com.example.booksearchexample.base.UiState
import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.util.DEFAULT_KEYWORD
import com.example.booksearchexample.util.FavoriteFilterType
import com.example.booksearchexample.util.FavoriteSortType

data class FavoriteUiState(
    override val loadState: LoadState = LoadState.Success,
    val bookList: List<BookUiModel> = emptyList(),
    val searchRequest: FavoriteRequest = FavoriteRequest()
) : UiState

data class FavoriteRequest(
    val inputKeyword: String = DEFAULT_KEYWORD,
    val sortType: FavoriteSortType = FavoriteSortType.ASCENDING,
    val filterType: FavoriteFilterType = FavoriteFilterType.ALL
)