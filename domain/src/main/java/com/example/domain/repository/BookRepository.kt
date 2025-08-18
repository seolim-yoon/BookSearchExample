package com.example.domain.repository

import com.example.domain.entity.BookEntity
import com.example.domain.entity.BookListEntity
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBook(
        keyword: String,
        sort: String,
        page: Int,
        pageSize: Int
    ): BookListEntity

    fun getFavoriteBookListAscending(
        keyword: String,
        start: Int,
        end: Int
    ): Flow<List<BookEntity>>

    fun getFavoriteBookListDescending(
        keyword: String,
        start: Int,
        end: Int
    ): Flow<List<BookEntity>>

    suspend fun getAllFavoriteBookListIds(): List<String>

    suspend fun addFavoriteBook(book: BookEntity): Long

    suspend fun removeFavoriteBook(book: BookEntity): Int
}