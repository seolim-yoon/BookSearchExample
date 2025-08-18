package com.example.booksearchexample.util

sealed interface ViewOptions {
    val title: String
}

enum class SearchSortType(
    override val title: String
) : ViewOptions {
    ACCURACY("정확도순"),
    LATEST("발간일순");

    companion object {
        fun fromValue(value: String): SearchSortType {
            return SearchSortType.entries.find { it.title == value } ?: ACCURACY
        }
    }
}

enum class FavoriteSortType(
    override val title: String
) : ViewOptions {
    ASCENDING("오름차순"),
    DESCENDING("내림차순");

    companion object {
        fun fromValue(value: String): FavoriteSortType {
            return FavoriteSortType.entries.find { it.title == value } ?: ASCENDING
        }
    }
}

enum class FavoriteFilterType(
    override val title: String,
    val min: Int,
    val max: Int
) : ViewOptions {
    ALL("전체", 0, Int.MAX_VALUE),
    LOW("0 ~ 10,000원", 0, 10_000),
    MID("10,001 ~ 50,000원", 10_001, 50_000),
    HIGH("50,001 ~ 100,000원", 50_001, 100_000),
    PREMIUM("100,001원 이상", 100_001, Int.MAX_VALUE);

    companion object {
        fun fromValue(value: String): FavoriteFilterType {
            return FavoriteFilterType.entries.find { it.title == value } ?: ALL
        }
    }
}

fun ViewOptions.getItemTitles(): List<String> {
    return when (this) {
        is SearchSortType -> SearchSortType.entries.map { it.title }
        is FavoriteSortType -> FavoriteSortType.entries.map { it.title }
        is FavoriteFilterType -> FavoriteFilterType.entries.map { it.title }
    }
}

enum class BottomSheetType {
    NONE, SORT, FILTER
}