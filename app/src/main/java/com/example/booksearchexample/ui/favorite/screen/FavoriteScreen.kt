package com.example.booksearchexample.ui.favorite.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.ui.common.screen.SortAndFilterBottomSheet
import com.example.booksearchexample.ui.favorite.contract.FavoriteUiEvent
import com.example.booksearchexample.ui.favorite.contract.FavoriteUiState
import com.example.booksearchexample.ui.search.item.SearchBarItem
import com.example.booksearchexample.ui.search.item.SearchResultItem
import com.example.booksearchexample.ui.search.item.SortInfoItem
import com.example.booksearchexample.util.BottomSheetType
import com.example.booksearchexample.util.DEFAULT_KEYWORD
import com.example.booksearchexample.util.FavoriteFilterType
import com.example.booksearchexample.util.FavoriteSortType

@Composable
internal fun FavoriteScreen(
    state: FavoriteUiState,
    onEvent: (FavoriteUiEvent) -> Unit,
    onClickBookItem: (BookUiModel) -> Unit,
) {
    var bottomSheetType by rememberSaveable { mutableStateOf(BottomSheetType.NONE) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBarItem(
            inputText = state.searchRequest.inputKeyword,
            onValueChange = { keyword -> onEvent(FavoriteUiEvent.InputKeyword(keyword)) },
            onClickClearBtn = { onEvent(FavoriteUiEvent.InputKeyword(DEFAULT_KEYWORD)) },
        )

        SortInfoItem(
            selectTitle = "${state.searchRequest.sortType.title}, ${state.searchRequest.filterType.title}",
            onClickSortBtn = { bottomSheetType = BottomSheetType.SORT },
            isVisibleFilter = true,
            onClickFilterBtn = { bottomSheetType = BottomSheetType.FILTER },
        )

        SearchResultItem(
            loadState = state.loadState,
            keyword = state.searchRequest.inputKeyword,
            bookList = state.bookList,
            onClickFavorite = { onEvent(FavoriteUiEvent.ClickFavorite(it)) },
            onClickBookItem = onClickBookItem
        )
    }

    if (bottomSheetType != BottomSheetType.NONE) {
        SortAndFilterBottomSheet(
            viewOptions = if (bottomSheetType == BottomSheetType.SORT) state.searchRequest.sortType else state.searchRequest.filterType,
            onClickOptionItem = { option ->
                when(bottomSheetType) {
                    BottomSheetType.SORT -> onEvent(FavoriteUiEvent.ClickSortType(FavoriteSortType.fromValue(option)))
                    BottomSheetType.FILTER -> onEvent(FavoriteUiEvent.ClickFilterType(FavoriteFilterType.fromValue(option)))
                    else -> {}
                }
                bottomSheetType = BottomSheetType.NONE
            },
            onDismissRequest = {
                bottomSheetType = BottomSheetType.NONE
            }
        )
    }
}