package com.example.data.datasource.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM Book " +
            "WHERE (:keyword = '' OR title LIKE '%' || :keyword || '%' OR authors LIKE '%' || :keyword || '%')  " +
            "AND price >= :start AND price < :end " +
            "ORDER BY title ASC")
    fun getFavoriteBookListAscending(keyword: String, start: Int, end: Int): Flow<List<Book>>

    @Query("SELECT * FROM Book " +
            "WHERE (:keyword = '' OR title LIKE '%' || :keyword || '%' OR authors LIKE '%' || :keyword || '%')  " +
            "AND price >= :start AND price < :end " +
            "ORDER BY title DESC")
    fun getFavoriteBookListDescending(keyword: String, start: Int, end: Int): Flow<List<Book>>

    @Query("SELECT book_id FROM Book")
    suspend fun getAllFavoriteBookListIds(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteBook(book: Book): Long

    @Delete
    suspend fun removeFavoriteBook(book: Book): Int
}