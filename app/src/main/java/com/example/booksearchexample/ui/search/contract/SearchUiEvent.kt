package com.example.booksearchexample.ui.search.contract

import com.example.booksearchexample.base.UiEvent
import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.util.SearchSortType

sealed interface SearchUiEvent: UiEvent {
    data class InputKeyword(val keyword: String) : SearchUiEvent
    data class ClickFavorite(val bookUiModel: BookUiModel) : SearchUiEvent
    data class ClickSortType(val sortType: SearchSortType) : SearchUiEvent
    data object LoadMore : SearchUiEvent
    data object Refresh : SearchUiEvent
}