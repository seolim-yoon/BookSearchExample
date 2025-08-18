package com.example.data.datasource.local

import com.example.data.datasource.local.db.Book
import com.example.data.datasource.local.db.BookDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookLocalDataSource @Inject constructor(
    private val bookDao: BookDao
) {
    fun getFavoriteBookListAscending(keyword: String, start: Int, end: Int): Flow<List<Book>> = bookDao.getFavoriteBookListAscending(keyword = keyword, start = start, end = end)

    fun getFavoriteBookListDescending(keyword: String, start: Int, end: Int): Flow<List<Book>> = bookDao.getFavoriteBookListDescending(keyword = keyword, start = start, end = end)

    suspend fun getAllFavoriteBookListTitles(): List<String> = bookDao.getAllFavoriteBookListTitles()

    suspend fun addFavoriteBook(book: Book): Long = bookDao.addFavoriteBook(book)

    suspend fun removeFavoriteBook(book: Book): Int = bookDao.removeFavoriteBook(book)
}