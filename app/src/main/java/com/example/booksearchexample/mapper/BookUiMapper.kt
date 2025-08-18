package com.example.booksearchexample.mapper

import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.util.searchDateFormatter
import com.example.domain.entity.BookEntity
import com.example.domain.entity.BookListEntity
import javax.inject.Inject

class BookUiMapper @Inject constructor() {
    fun mapToBookUiModelList(bookListEntity: BookListEntity, currentPage: Int, keyword: String): List<BookUiModel> =
        bookListEntity.bookList.mapIndexed { index, book ->
            BookUiModel(
                id = "${keyword}_${currentPage}_${index}",
                title = book.title,
                thumbnail = book.thumbnail,
                authors = book.authors,
                publisher = book.publisher,
                datetime = searchDateFormatter(book.datetime),
                price = book.price,
                salePrice = book.salePrice,
                contents = book.contents,
                isFavorite = book.isFavorite
            )
        }

    fun mapToBookUiModel(book: BookEntity): BookUiModel =
        BookUiModel(
            id = book.id,
            title = book.title,
            thumbnail = book.thumbnail,
            authors = book.authors,
            publisher = book.publisher,
            datetime = book.datetime,
            price = book.price,
            salePrice = book.salePrice,
            contents = book.contents,
            isFavorite = book.isFavorite
        )

    fun mapToBookEntity(book: BookUiModel): BookEntity =
        BookEntity(
            id = book.id,
            title = book.title,
            thumbnail = book.thumbnail,
            authors = book.authors,
            publisher = book.publisher,
            datetime = book.datetime,
            price = book.price,
            salePrice = book.salePrice,
            contents = book.contents,
            isFavorite = book.isFavorite
        )
}