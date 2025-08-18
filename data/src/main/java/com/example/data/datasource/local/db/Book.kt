package com.example.data.datasource.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey
    @ColumnInfo(name = "book_id")
    val id: String = "",
    val title: String = "",
    val thumbnail: String = "",
    val authors: String = "",
    val publisher: String = "",
    val datetime: String = "",
    val price: Int = 0,
    val salePrice: Int = 0,
    val contents: String = ""
)