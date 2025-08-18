package com.example.booksearchexample.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.booksearchexample.model.BookUiModel

class BookItemParameterProvider(
    override val values: Sequence<BookUiModel> = sequenceOf(
        defaultCase,
        longTitleCase,
        favoriteCase
    )
): PreviewParameterProvider<BookUiModel>

private val defaultCase = BookUiModel(
    id = "",
    title = "타이틀 입니다.",
    thumbnail = "https://www.asdfasdfasdfasasdfasdffasdfasdfasdfasasdfasdff.com",
    authors = "저자",
    publisher = "출판사",
    datetime = "2024년 3월 11일",
    price = 10000,
    salePrice = 7000,
    contents = "콘텐츠 입니다.",
    isFavorite = false
)

private val longTitleCase = BookUiModel(
    id = "",
    title = "타이틀 입니다. 타이틀 입니다. 타이틀 입니다. 타이틀 입니다. 타이틀 입니다. 타이틀 입니다. 타이틀 입니다. 타이틀 입니다.",
    thumbnail = "https://www.asdfasdfasdfasasdfasdffasdfasdfasdfasasdfasdff.com",
    authors = "저자",
    publisher = "출판사",
    datetime = "2024년 3월 11일",
    price = 10000,
    salePrice = 7000,
    contents = "콘텐츠 입니다.",
    isFavorite = false
)

private val favoriteCase = BookUiModel(
    id = "",
    title = "타이틀 입니다.",
    thumbnail = "https://www.asdfasdfasdfasasdfasdffasdfasdfasdfasasdfasdff.com",
    authors = "저자",
    publisher = "출판사",
    datetime = "2024년 3월 11일",
    price = 10000,
    salePrice = 7000,
    contents = "콘텐츠 입니다.",
    isFavorite = true
)

val PreviewBookList = List(5) { index ->
    BookUiModel(
        id = index.toString(),
        title = "타이틀 입니다.",
        thumbnail = "https://www.asdfasdfasdfasasdfasdffasdfasdfasdfasasdfasdff.com",
        authors = "저자",
        publisher = "출판사",
        datetime = "2024년 3월 11일",
        price = 10000,
        salePrice = 7000,
        contents = "콘텐츠 입니다.",
        isFavorite = false
    )
}