package com.example.booksearchexample.ui.common.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.ui.favorite.FavoriteViewModel
import com.example.booksearchexample.ui.favorite.screen.FavoriteScreen
import com.example.booksearchexample.ui.search.SearchViewModel
import com.example.booksearchexample.ui.search.screen.SearchScreen
import com.example.booksearchexample.util.AppState
import com.example.booksearchexample.util.ScreenType

@Composable
internal fun BottomTabNavGraph(
    bottomAppState: AppState,
    navigateToDetail: (BookUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = bottomAppState.navController,
        startDestination = ScreenType.Search,
        modifier = modifier
    ) {
        composable<ScreenType.Search> {
            val viewModel: SearchViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            SearchScreen(
                state = state,
                onEvent = viewModel::onEvent,
                onClickBookItem = navigateToDetail
            )
        }

        composable<ScreenType.Favorite> {
            val viewModel: FavoriteViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            FavoriteScreen(
                state = state,
                onEvent = viewModel::onEvent,
                onClickBookItem = navigateToDetail
            )
        }
    }
}