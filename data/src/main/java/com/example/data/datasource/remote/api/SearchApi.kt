package com.example.data.datasource.remote.api

import com.example.data.dto.BookDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("v3/search/book")
    suspend fun searchBook(
        @Query("query") keyword: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") pageSize: Int
    ): BookDTO
}