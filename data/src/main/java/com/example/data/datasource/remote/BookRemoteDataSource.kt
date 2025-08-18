package com.example.data.datasource.remote

import com.example.data.datasource.remote.api.SearchApi
import com.example.data.dto.BookDTO
import javax.inject.Inject

class BookRemoteDataSource @Inject constructor(
    private val searchApi: SearchApi
) {
    suspend fun searchBook(
        keyword: String,
        sort: String,
        page: Int,
        pageSize: Int
    ): BookDTO = searchApi.searchBook(
        keyword = keyword,
        sort = sort,
        page = page,
        pageSize = pageSize
    )
}