package com.example.booksearchexample.ui.common.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.booksearchexample.ui.theme.BookSearchExampleTheme

@Composable
internal fun SortAndFilterItem(
    title: String,
    isSelected: Boolean,
    onClickSortBtn: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .clickable {
                if (!isSelected) onClickSortBtn()
            }
            .padding(15.dp)
    ) {
        Text(
            text = title,
            style = if (isSelected) MaterialTheme.typography.titleSmall else MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewSortAndFilterItem(@PreviewParameter(SortParameterProvider::class) isSelected: Boolean) {
    BookSearchExampleTheme {
        SortAndFilterItem(
            title = "정확도순",
            isSelected = isSelected,
            onClickSortBtn = {}
        )
    }
}

class SortParameterProvider(
    override val values: Sequence<Boolean> = sequenceOf(
        trueCase,
        falseCase
    )
): PreviewParameterProvider<Boolean>

private const val trueCase = true
private const val falseCase = false
