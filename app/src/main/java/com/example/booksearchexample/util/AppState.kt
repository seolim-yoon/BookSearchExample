package com.example.booksearchexample.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.booksearchexample.model.BookUiModel

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) { AppState(navController) }

@Stable
class AppState(
    val navController: NavHostController
) {
    val currentRoute: BottomNavType
        @Composable get() = BottomNavType.getBottomNavType(navController.currentBackStackEntryAsState().value?.destination?.route?.split(".")?.lastOrNull() ?: "")

    private val graphId: Int
        get() = navController.graph.id

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: ScreenType) {
        navController.navigate(route) {
            launchSingleTop = true
            restoreState = true
            popUpTo(graphId) {
                saveState = true
            }
        }
    }

    fun navigateToDetailScreen(bookUiModel: BookUiModel) {
        navController.navigate(ScreenType.Detail(bookUiModel = bookUiModel))
    }
}