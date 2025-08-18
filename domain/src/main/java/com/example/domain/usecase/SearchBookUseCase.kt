package com.example.domain.usecase

import com.example.domain.entity.BookListEntity
import com.example.domain.repository.BookRepository
import javax.inject.Inject

class SearchBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(
        keyword: String,
        sort: String,
        page: Int,
        pageSize: Int
    ): BookListEntity {
        val favoriteIds = bookRepository.getAllFavoriteBookListIds()
        val result = bookRepository.searchBook(
            keyword = keyword,
            sort = sort,
            page = page,
            pageSize = pageSize
        )

        return BookListEntity(
            isEnd = result.isEnd,
            bookList = result.bookList.map { entity ->
                entity.copy(
                    isFavorite = favoriteIds.contains(entity.id)
                )
            }
        )
    }
}