package com.example.booksearchexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import com.example.booksearchexample.ui.common.graph.NavHostGraph
import com.example.booksearchexample.ui.theme.BookSearchExampleTheme
import com.example.booksearchexample.util.rememberAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookSearchExampleTheme {
                val appState = rememberAppState()
                val containerColor = MaterialTheme.colorScheme.background

                Surface(
                    color = containerColor,
                    contentColor = contentColorFor(containerColor)
                ) {
                    NavHostGraph(
                        appState = appState
                    )
                }
            }
        }
    }
}
