package com.example.data.mapper

import com.example.data.datasource.local.db.Book
import com.example.data.dto.BookDTO
import com.example.domain.entity.BookEntity
import com.example.domain.entity.BookListEntity
import javax.inject.Inject

class BookEntityMapper @Inject constructor() {
    fun mapToBookListEntity(dto: BookDTO): BookListEntity =
        BookListEntity(
            isEnd = dto.meta.isEnd,
            bookList = mapDTOToBookEntityList(dto.documents)
        )

    private fun mapDTOToBookEntityList(bookList: List<BookDTO.Document>): List<BookEntity> =
        bookList.map { book ->
            BookEntity(
                id = book.isbn + "_" + book.title,
                title = book.title,
                thumbnail = book.thumbnail,
                authors = book.authors.joinToString(", "),
                publisher = book.publisher,
                datetime = book.datetime,
                price = book.price,
                salePrice = book.salePrice,
                contents = book.contents
            )
        }

    fun mapToBookEntityList(bookList: List<Book>): List<BookEntity> =
        bookList.map { book ->
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
                isFavorite = true
            )
        }

    fun mapToBook(bookEntity: BookEntity): Book =
        Book(
            id = bookEntity.id,
            title = bookEntity.title,
            thumbnail = bookEntity.thumbnail,
            authors = bookEntity.authors,
            publisher = bookEntity.publisher,
            datetime = bookEntity.datetime,
            price = bookEntity.price,
            salePrice = bookEntity.salePrice,
            contents = bookEntity.contents
        )
}