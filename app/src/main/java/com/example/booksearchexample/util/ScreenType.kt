package com.example.booksearchexample.util

import com.example.booksearchexample.model.BookUiModel
import kotlinx.serialization.Serializable

sealed interface ScreenType {
    @Serializable
    data object Main : ScreenType
    @Serializable
    data object Search : ScreenType
    @Serializable
    data object Favorite : ScreenType
    @Serializable
    data class Detail(
        val bookUiModel: BookUiModel
    ) : ScreenType
}