package com.example.booksearchexample.ui.common.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booksearchexample.ui.common.item.SortAndFilterItem
import com.example.booksearchexample.ui.theme.BookSearchExampleTheme
import com.example.booksearchexample.util.SORT_ITEM_TYPE
import com.example.booksearchexample.util.SearchSortType
import com.example.booksearchexample.util.ViewOptions
import com.example.booksearchexample.util.getItemTitles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SortAndFilterBottomSheet(
    viewOptions: ViewOptions,
    onClickOptionItem: (String) -> Unit,
    onDismissRequest: () -> Unit,
) {

    val bottomState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = bottomState,
        onDismissRequest = onDismissRequest
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 20.dp)
        ) {

            LazyColumn(
                contentPadding = PaddingValues(15.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    key = { it },
                    contentType = { SORT_ITEM_TYPE },
                    items = viewOptions.getItemTitles()
                ) { optionTitle ->
                    SortAndFilterItem(
                        title = optionTitle,
                        isSelected = optionTitle == viewOptions.title,
                        onClickSortBtn = { onClickOptionItem(optionTitle) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSortAndFilterBottomSheet() {
    BookSearchExampleTheme {
        SortAndFilterBottomSheet(
            viewOptions = SearchSortType.ACCURACY,
            onClickOptionItem = {},
            onDismissRequest = {}
        )
    }
}
