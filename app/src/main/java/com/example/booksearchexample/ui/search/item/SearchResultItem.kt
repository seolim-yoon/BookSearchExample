package com.example.booksearchexample.ui.search.item

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.booksearchexample.R
import com.example.booksearchexample.base.LoadState
import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.ui.common.screen.EmptyScreen
import com.example.booksearchexample.ui.common.screen.ErrorScreen

@Composable
internal fun SearchResultItem(
    loadState: LoadState,
    keyword: String,
    bookList: List<BookUiModel>,
    onClickFavorite: (BookUiModel) -> Unit,
    onClickBookItem: (BookUiModel) -> Unit,
    loadMoreItem: () -> Unit = {},
    onRefresh: () -> Unit = {}
) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
        }.collect { lastVisibleIndex ->
            val totalCount = listState.layoutInfo.totalItemsCount
            if (totalCount > 0 && lastVisibleIndex >= totalCount - 1) {
                loadMoreItem()
            }
        }
    }

    LaunchedEffect(loadState) {
        if (loadState is LoadState.Idle) {
            listState.scrollToItem(0)
        }
    }

    when (loadState) {
        is LoadState.Idle -> {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }

        is LoadState.Error -> {
            ErrorScreen(
                errorMessage = loadState.error.message.toString(),
                onRefresh = onRefresh
            )
        }

        else -> {
            if (bookList.isEmpty() && keyword.isNotBlank()) {
                EmptyScreen(
                    text = stringResource(R.string.empty_msg)
                )
            } else {
                BookListItem(
                    listState = listState,
                    bookList = bookList,
                    onClickFavorite = onClickFavorite,
                    onClickBookItem = onClickBookItem
                )
            }
        }
    }
}