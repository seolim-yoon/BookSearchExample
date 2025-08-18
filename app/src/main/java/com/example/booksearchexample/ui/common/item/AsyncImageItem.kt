package com.example.booksearchexample.ui.common.item

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import coil.compose.AsyncImage

@Composable
internal fun AsyncImageItem(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    if (LocalInspectionMode.current) {
        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = null,
            modifier = modifier
        )
    } else {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }
}