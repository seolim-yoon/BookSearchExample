package com.example.booksearchexample.util

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.booksearchexample.R

enum class BottomNavType(
    val route: ScreenType,
    val title: String,
    @StringRes val bottomTitleRes: Int,
    val icon: ImageVector
) {
    SEARCH(
        route = ScreenType.Search,
        title = "Search",
        bottomTitleRes = R.string.search,
        icon = Icons.Default.Search
    ),

    FAVORITE(
        route = ScreenType.Favorite,
        title = "Favorite",
        bottomTitleRes = R.string.favorite,
        icon = Icons.Default.Favorite
    );

    companion object {
        fun getBottomNavType(route: String): BottomNavType =
            entries.find { route == it.title } ?: BottomNavType.SEARCH
    }
}