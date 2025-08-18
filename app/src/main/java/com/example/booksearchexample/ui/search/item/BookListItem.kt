package com.example.booksearchexample.ui.search.item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.ui.theme.BookSearchExampleTheme
import com.example.booksearchexample.util.BOOK_ITEM_TYPE
import com.example.booksearchexample.util.PreviewBookList

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun BookListItem(
    listState: LazyListState,
    bookList: List<BookUiModel>,
    onClickFavorite: (BookUiModel) -> Unit,
    onClickBookItem: (BookUiModel) -> Unit
) {
    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                key = { it.key },
                contentType = { BOOK_ITEM_TYPE },
                items = bookList
            ) { book ->
               BookItem(
                   book = book,
                   onClickFavorite = {
                       onClickFavorite(book)
                   },
                   onClickBookItem = {
                       onClickBookItem(book)
                   }
               )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBookListItem() {
    BookSearchExampleTheme {
        BookListItem(
            listState = rememberLazyListState(),
            bookList = PreviewBookList,
            onClickFavorite = {},
            onClickBookItem = {}
        )
    }
}