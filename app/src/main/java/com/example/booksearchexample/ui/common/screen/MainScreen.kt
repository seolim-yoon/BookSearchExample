package com.example.booksearchexample.ui.common.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.booksearchexample.ui.common.graph.BottomTabNavGraph
import com.example.booksearchexample.ui.common.item.BottomNavigationItem
import com.example.booksearchexample.ui.common.item.TopAppBarItem
import com.example.booksearchexample.util.AppState
import com.example.booksearchexample.util.rememberAppState

@Composable
internal fun MainScreen(
    mainAppState: AppState
) {
    val bottomAppState: AppState = rememberAppState()

    Scaffold(
        topBar = {
            TopAppBarItem(
                topBarTitle = stringResource(bottomAppState.currentRoute.bottomTitleRes)
            )
        },
        bottomBar = {
            BottomNavigationItem(
                navigateToRoute = bottomAppState::navigateToBottomBarRoute
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        BottomTabNavGraph(
            bottomAppState = bottomAppState,
            navigateToDetail = mainAppState::navigateToDetailScreen,
            modifier = Modifier.padding(innerPadding)
        )
    }
}