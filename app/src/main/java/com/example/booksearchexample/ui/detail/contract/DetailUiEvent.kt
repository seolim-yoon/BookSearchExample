package com.example.booksearchexample.ui.detail.contract

import com.example.booksearchexample.base.UiEvent
import com.example.booksearchexample.model.BookUiModel

sealed interface DetailUiEvent: UiEvent {
    data class ClickFavorite(val bookUiModel: BookUiModel) : DetailUiEvent
}