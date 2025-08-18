package com.example.booksearchexample.mapper

import com.example.booksearchexample.util.FavoriteSortType
import com.example.domain.util.SortType
import javax.inject.Inject

class DomainMapper @Inject constructor() {
    fun mapToSortType(sortType: FavoriteSortType): SortType =
        when (sortType) {
            FavoriteSortType.ASCENDING -> SortType.ASCENDING
            FavoriteSortType.DESCENDING -> SortType.DESCENDING
        }
}