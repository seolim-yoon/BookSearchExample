package com.example.domain.entity

data class BookListEntity(
    val isEnd: Boolean,
    val bookList: List<BookEntity>
)

data class BookEntity(
    val id: String = "",
    val title: String = "",
    val thumbnail: String = "",
    val authors: String = "",
    val publisher: String = "",
    val datetime: String = "",
    val price: Int = 0,
    val salePrice: Int = 0,
    val contents: String = "",
    val isFavorite: Boolean = false
)
