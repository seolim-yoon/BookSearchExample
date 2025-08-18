package com.example.booksearchexample.ui.common.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.ui.common.screen.MainScreen
import com.example.booksearchexample.ui.detail.DetailViewModel
import com.example.booksearchexample.ui.detail.screen.DetailScreen
import com.example.booksearchexample.util.AppState
import com.example.booksearchexample.util.ScreenType
import com.example.booksearchexample.util.serializableType
import kotlin.reflect.typeOf

@Composable
internal fun NavHostGraph(
    appState: AppState
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = ScreenType.Main
    ) {
        composable<ScreenType.Main> {
            MainScreen(
                mainAppState = appState
            )
        }

        composable<ScreenType.Detail>(
            typeMap = mapOf(typeOf<BookUiModel>() to serializableType<BookUiModel>())
        ) {
            val viewModel: DetailViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            DetailScreen(
                state = state,
                onEvent = viewModel::onEvent,
                onClickBackNav = appState::navigateUp
            )
        }
    }
}