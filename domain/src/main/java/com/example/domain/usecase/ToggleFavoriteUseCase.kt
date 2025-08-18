package com.example.domain.usecase

import com.example.domain.entity.BookEntity
import com.example.domain.repository.BookRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(
        isFavorite: Boolean,
        book: BookEntity
    ): Boolean =
        if (isFavorite) {
            bookRepository.addFavoriteBook(book) > 0
        } else {
            bookRepository.removeFavoriteBook(book) > 0
        }
}