package com.example.booksearchexample.ui.search.screen

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
import com.example.booksearchexample.ui.search.contract.SearchUiEvent
import com.example.booksearchexample.ui.search.contract.SearchUiState
import com.example.booksearchexample.ui.search.item.SearchBarItem
import com.example.booksearchexample.ui.search.item.SearchResultItem
import com.example.booksearchexample.ui.search.item.SortInfoItem
import com.example.booksearchexample.util.DEFAULT_KEYWORD
import com.example.booksearchexample.util.SearchSortType

@Composable
internal fun SearchScreen(
    state: SearchUiState,
    onEvent: (SearchUiEvent) -> Unit,
    onClickBookItem: (BookUiModel) -> Unit,
) {
    var isShowBottomSheet by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBarItem(
            inputText = state.searchRequest.inputKeyword,
            onValueChange = { keyword -> onEvent(SearchUiEvent.InputKeyword(keyword)) },
            onClickClearBtn = { onEvent(SearchUiEvent.InputKeyword(DEFAULT_KEYWORD)) }
        )

        if (state.searchRequest.inputKeyword.isNotBlank()) {
            SortInfoItem(
                selectTitle = state.searchRequest.sortType.title,
                onClickSortBtn = { isShowBottomSheet = true }
            )

            SearchResultItem(
                loadState = state.loadState,
                keyword = state.searchRequest.inputKeyword,
                bookList = state.bookList,
                onClickFavorite = { onEvent(SearchUiEvent.ClickFavorite(it)) },
                onClickBookItem = onClickBookItem,
                loadMoreItem = { onEvent(SearchUiEvent.LoadMore) },
                onRefresh = { onEvent(SearchUiEvent.Refresh) }
            )
        }
    }

    if (isShowBottomSheet) {
        SortAndFilterBottomSheet(
            viewOptions = state.searchRequest.sortType,
            onClickOptionItem = { option ->
                onEvent(SearchUiEvent.ClickSortType(SearchSortType.fromValue(option)))
                isShowBottomSheet = false
            },
            onDismissRequest = {
                isShowBottomSheet = false
            }
        )
    }
}