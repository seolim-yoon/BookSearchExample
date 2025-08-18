package com.example.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDTO(
    val documents: List<Document> = listOf(),
    val meta: Meta = Meta()
) {
    @Serializable
    data class Document(
        val authors: List<String> = listOf(),
        val contents: String = "",
        val datetime: String = "",
        val isbn: String = "",
        val price: Int = 0,
        val publisher: String = "",
        val salePrice: Int = 0,
        val status: String = "",
        val thumbnail: String = "",
        val title: String = "",
        val translators: List<String> = listOf(),
        val url: String = ""
    )

    @Serializable
    data class Meta(
        @SerialName("is_end")
        val isEnd: Boolean = false,
        @SerialName("pageable_count")
        val pageableCount: Int = 0,
        @SerialName("total_count")
        val totalCount: Int = 0
    )
}