package com.example.booksearchexample.ui.detail.contract

import com.example.booksearchexample.base.LoadState
import com.example.booksearchexample.base.UiState
import com.example.booksearchexample.model.BookUiModel

data class DetailUiState(
    override val loadState: LoadState = LoadState.Success,
    val book: BookUiModel = BookUiModel()
): UiState