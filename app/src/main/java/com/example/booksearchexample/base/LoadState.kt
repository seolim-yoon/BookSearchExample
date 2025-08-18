package com.example.booksearchexample.base

sealed interface LoadState {
    data object Idle: LoadState
    data object Loading : LoadState
    data object Success : LoadState
    data object Complete : LoadState
    data class Error(val error: Throwable) : LoadState
}