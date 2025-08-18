package com.example.booksearchexample.ui.search.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booksearchexample.R
import com.example.booksearchexample.ui.theme.BookSearchExampleTheme
import com.example.booksearchexample.util.SearchSortType

@Composable
internal fun SortInfoItem(
    selectTitle: String,
    onClickSortBtn: () -> Unit,
    isVisibleFilter: Boolean = false,
    onClickFilterBtn: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 20.dp)
    ) {

        Text(
            text = selectTitle,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.weight(1f)
        )

        if (isVisibleFilter) {
            SortButtonItem(
                title = stringResource(R.string.filter),
                onClickSortBtn = {
                    onClickFilterBtn()
                }
            )
        }

        SortButtonItem(
            title = stringResource(R.string.sort),
            onClickSortBtn = {
                onClickSortBtn()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSortInfoItem() {
    BookSearchExampleTheme {
        SortInfoItem(
            selectTitle = SearchSortType.ACCURACY.title,
            onClickSortBtn = {},
            isVisibleFilter = true
        )
    }
}
