package com.example.booksearchexample.ui.detail.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booksearchexample.R
import com.example.booksearchexample.ui.theme.BookSearchExampleTheme

@Composable
internal fun ContentInfoItem(
    content: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.content),
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = content,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewContentInfoItem() {
    BookSearchExampleTheme {
        ContentInfoItem(
            content = "contents"
        )
    }
}
