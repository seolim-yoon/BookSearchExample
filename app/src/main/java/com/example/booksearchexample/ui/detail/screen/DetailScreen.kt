package com.example.booksearchexample.ui.detail.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.ui.common.item.TopAppBarItem
import com.example.booksearchexample.ui.detail.contract.DetailUiEvent
import com.example.booksearchexample.ui.detail.contract.DetailUiState
import com.example.booksearchexample.ui.detail.item.BookInfoItem
import com.example.booksearchexample.ui.detail.item.ContentInfoItem
import com.example.booksearchexample.ui.theme.BookSearchExampleTheme
import com.example.booksearchexample.util.BookItemParameterProvider

@Composable
internal fun DetailScreen(
    state: DetailUiState,
    onEvent: (DetailUiEvent) -> Unit,
    onClickBackNav: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarItem(
                isVisibleImage = true,
                isFavorite = state.book.isFavorite,
                onClickBackNav = onClickBackNav,
                onClickFavoriteBtn = { onEvent(DetailUiEvent.ClickFavorite(state.book)) },
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            verticalArrangement = Arrangement.spacedBy(25.dp),
            modifier = Modifier
                .padding(innerPadding)
                .padding(25.dp)
        ) {
            Text(
                text = state.book.title,
                style = MaterialTheme.typography.titleLarge
            )
            BookInfoItem(
                book = state.book
            )
            ContentInfoItem(
                content = state.book.contents
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewDetailScreen(@PreviewParameter(BookItemParameterProvider::class) book: BookUiModel) {
    BookSearchExampleTheme {
        DetailScreen(
            state = DetailUiState(),
            onEvent = {},
            onClickBackNav = {}
        )
    }
}
