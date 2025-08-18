package com.example.booksearchexample.ui.search.item

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booksearchexample.R
import com.example.booksearchexample.ui.theme.BookSearchExampleTheme

@Composable
internal fun SortButtonItem(
    title: String,
    onClickSortBtn: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(5.dp)
            .border(
            width = 1.dp,
            shape = RoundedCornerShape(12.dp),
            color = Color.LightGray
        ).clip(
            shape = RoundedCornerShape(12.dp)
        ).clickable {
            onClickSortBtn()
        }
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSortButtonItem() {
    BookSearchExampleTheme {
        SortButtonItem(
            title = stringResource(R.string.sort),
            onClickSortBtn = {}
        )
    }
}
