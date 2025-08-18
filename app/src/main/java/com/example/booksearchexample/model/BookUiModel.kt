package com.example.booksearchexample.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class BookUiModel(
    val id: String = "",
    val key: String = "",
    val title: String = "",
    val thumbnail: String = "",
    val authors: String = "",
    val publisher: String = "",
    val datetime: String = "",
    val price: Int = 0,
    val salePrice: Int = 0,
    val contents: String = "",
    val isFavorite: Boolean = false
): Parcelable
