package com.example.booksearchexample.ui.favorite.contract

import com.example.booksearchexample.base.UiEvent
import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.util.FavoriteFilterType
import com.example.booksearchexample.util.FavoriteSortType

sealed interface FavoriteUiEvent: UiEvent {
    data class InputKeyword(val keyword: String) : FavoriteUiEvent
    data class ClickFavorite(val bookUiModel: BookUiModel) : FavoriteUiEvent
    data class ClickSortType(val sortType: FavoriteSortType) : FavoriteUiEvent
    data class ClickFilterType(val filterType: FavoriteFilterType) : FavoriteUiEvent
}