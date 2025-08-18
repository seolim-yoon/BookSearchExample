package com.example.booksearchexample.ui.search.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.booksearchexample.R
import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.ui.common.item.AsyncImageItem
import com.example.booksearchexample.ui.theme.BookSearchExampleTheme
import com.example.booksearchexample.util.BookItemParameterProvider

@Composable
internal fun BookItem(
    book: BookUiModel,
    onClickFavorite: () -> Unit,
    onClickBookItem: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(130.dp)
            .background(
                shape = RoundedCornerShape(12.dp),
                color = Color.White
            ).clip(
                shape = RoundedCornerShape(12.dp)
            ).clickable {
                onClickBookItem()
            }
            .padding(5.dp)
    ) {
        AsyncImageItem(
            imageUrl = book.thumbnail,
            modifier = Modifier.fillMaxWidth(0.25f)
                .padding(5.dp)
                .clip(
                    shape = RoundedCornerShape(12.dp)
                )
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
                .padding(start = 10.dp)
        ) {
            Row{
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = book.title,
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

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
                }

                val vector = if (book.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder
                Image(
                    imageVector = vector,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onClickFavorite()
                        }
                        .padding(8.dp)
                )
            }

            Text(
                text = stringResource(R.string.price_won, book.price),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
                    .padding(5.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBookItem(@PreviewParameter(BookItemParameterProvider::class) book: BookUiModel) {
    BookSearchExampleTheme {
        BookItem(
            book = book,
            onClickFavorite = {},
            onClickBookItem = {}
        )
    }
}
