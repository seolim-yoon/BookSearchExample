package com.example.booksearchexample.ui.detail.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.booksearchexample.R
import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.ui.common.item.AsyncImageItem
import com.example.booksearchexample.ui.theme.BookSearchExampleTheme
import com.example.booksearchexample.util.BookItemParameterProvider

@Composable
internal fun BookInfoItem(
    book: BookUiModel
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImageItem(
            imageUrl = book.thumbnail,
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .aspectRatio(3 / 4f)
                .clip(RoundedCornerShape(12.dp))
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(10.dp)
        ) {

            Text(
                text = stringResource(R.string.publisher, book.publisher),
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = stringResource(R.string.authors, book.authors),
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = stringResource(R.string.datetime, book.datetime),
                style = MaterialTheme.typography.bodySmall
            )


            Text(
                text = stringResource(R.string.price, book.price),
                style = MaterialTheme.typography.bodySmall
            )

            if (book.salePrice != 0) {
                Text(
                    text = stringResource(R.string.sale_price, book.salePrice),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBookInfoItem(@PreviewParameter(BookItemParameterProvider::class) book: BookUiModel) {
    BookSearchExampleTheme {
        BookInfoItem(
            book = book
        )
    }
}
