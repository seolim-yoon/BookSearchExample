package com.example.booksearchexample.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun searchDateFormatter(date: String): String =
    try {
        LocalDateTime
            .parse(date, DateTimeFormatter.ISO_DATE_TIME)
            .format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"))
    } catch (e: Exception) {
        ""
    }
