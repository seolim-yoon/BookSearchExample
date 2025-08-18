package com.example.booksearchexample.ui.search.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booksearchexample.R
import com.example.booksearchexample.ui.theme.BookSearchExampleTheme
import com.example.booksearchexample.util.DEFAULT_KEYWORD

@Composable
internal fun SearchBarItem(
    inputText: String,
    onValueChange: (String) -> Unit,
    onClickClearBtn: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = inputText,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        ),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(vertical = 15.dp)
            ) {
                Image(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 20.dp, end = 10.dp)
                )

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (inputText.isEmpty()) {
                        Text(
                            text = stringResource(R.string.search_place_holder),
                            color = Color.Black
                        )
                    }
                    innerTextField()
                }

                if (inputText.isNotBlank()) {
                    Image(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                onClickClearBtn()
                            }
                            .padding(end = 20.dp)
                            .size(20.dp)
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
            }
        ),
        modifier = Modifier.padding(
            horizontal = 20.dp,
            vertical = 10.dp
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchBarItem() {
    BookSearchExampleTheme {
        SearchBarItem(
            inputText = DEFAULT_KEYWORD,
            onValueChange = { },
            onClickClearBtn = { }
        )
    }
}

