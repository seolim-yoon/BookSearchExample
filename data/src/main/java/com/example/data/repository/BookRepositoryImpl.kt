package com.example.data.repository

import com.example.data.datasource.local.BookLocalDataSource
import com.example.data.datasource.remote.BookRemoteDataSource
import com.example.data.mapper.BookEntityMapper
import com.example.domain.entity.BookEntity
import com.example.domain.entity.BookListEntity
import com.example.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookLocalDataSource: BookLocalDataSource,
    private val bookRemoteDataSource: BookRemoteDataSource,
    private val bookEntityMapper: BookEntityMapper
) : BookRepository {
    override suspend fun searchBook(
        keyword: String,
        sort: String,
        page: Int,
        pageSize: Int
    ): BookListEntity = bookEntityMapper.mapToBookListEntity(
        bookRemoteDataSource.searchBook(
            keyword = keyword,
            sort = sort,
            page = page,
            pageSize = pageSize
        )
    )

    override fun getFavoriteBookListAscending(
        keyword: String,
        start: Int,
        end: Int
    ): Flow<List<BookEntity>> =
        bookLocalDataSource.getFavoriteBookListAscending(
            keyword = keyword,
            start = start,
            end = end
        ).map {
            bookEntityMapper.mapToBookEntityList(it)
        }

    override fun getFavoriteBookListDescending(
        keyword: String,
        start: Int,
        end: Int
    ): Flow<List<BookEntity>> =
        bookLocalDataSource.getFavoriteBookListDescending(
            keyword = keyword,
            start = start,
            end = end
        ).map {
            bookEntityMapper.mapToBookEntityList(it)
        }

    override suspend fun getAllFavoriteBookListTitles(): List<String> =
        bookLocalDataSource.getAllFavoriteBookListTitles()

    override suspend fun addFavoriteBook(book: BookEntity): Long =
        bookLocalDataSource.addFavoriteBook(bookEntityMapper.mapToBook(book))

    override suspend fun removeFavoriteBook(book: BookEntity): Int =
        bookLocalDataSource.removeFavoriteBook(bookEntityMapper.mapToBook(book))
}