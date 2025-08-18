package com.example.domain.usecase

import com.example.domain.entity.BookEntity
import com.example.domain.repository.BookRepository
import com.example.domain.util.SortType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteBookListUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    operator fun invoke(
        keyword: String,
        sortType: SortType = SortType.ASCENDING,
        start: Int = 0,
        end: Int = Int.MAX_VALUE
    ): Flow<List<BookEntity>> =
        when (sortType) {
            SortType.ASCENDING -> bookRepository.getFavoriteBookListAscending(keyword = keyword, start = start, end = end)
            SortType.DESCENDING -> bookRepository.getFavoriteBookListDescending(keyword = keyword, start = start, end = end)
        }
}