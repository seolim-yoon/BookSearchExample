package com.example.booksearchexample.ui.common.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booksearchexample.ui.theme.BookSearchExampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopAppBarItem(
    topBarTitle: String = "",
    isVisibleImage: Boolean = false,
    isFavorite: Boolean = false,
    onClickBackNav: () -> Unit = {},
    onClickFavoriteBtn: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = topBarTitle,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            if (isVisibleImage) {
                IconButton(
                    onClick = onClickBackNav
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        modifier = Modifier
                    )
                }
            }
        },
        actions = {
            if (isVisibleImage) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onClickFavoriteBtn()
                    }
                )
            }
        },
        modifier = Modifier.padding(horizontal = 15.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewTopAppBarItem() {
    BookSearchExampleTheme {
        TopAppBarItem(
            topBarTitle = "TopBar",
            isVisibleImage = true
        )
    }
}