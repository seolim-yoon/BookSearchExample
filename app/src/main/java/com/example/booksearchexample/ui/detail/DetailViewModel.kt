package com.example.booksearchexample.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.booksearchexample.base.BaseViewModel
import com.example.booksearchexample.mapper.BookUiMapper
import com.example.booksearchexample.model.BookUiModel
import com.example.booksearchexample.ui.detail.contract.DetailUiEvent
import com.example.booksearchexample.ui.detail.contract.DetailUiState
import com.example.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val bookUiMapper: BookUiMapper
) : BaseViewModel<DetailUiState, DetailUiEvent, Nothing>() {
    override fun createInitialState(): DetailUiState = DetailUiState()

    init {
        setState {
            copy(
                book = savedStateHandle.get<BookUiModel>("bookUiModel") ?: BookUiModel()
            )
        }
    }

    private fun clickFavorite(bookUiModel: BookUiModel) {
        val updatedFavorite = !bookUiModel.isFavorite

        viewModelScope.launch(Dispatchers.IO) {
            val isSuccess = toggleFavoriteUseCase(
                isFavorite = updatedFavorite,
                book = bookUiMapper.mapToBookEntity(bookUiModel)
            )

            if (isSuccess) {
                setState {
                    copy(
                        book = book.copy(isFavorite = updatedFavorite)
                    )
                }
            }
        }
    }

    override fun onEvent(event: DetailUiEvent) {
        when (event) {
            is DetailUiEvent.ClickFavorite -> {
                clickFavorite(event.bookUiModel)
            }
        }
    }
}