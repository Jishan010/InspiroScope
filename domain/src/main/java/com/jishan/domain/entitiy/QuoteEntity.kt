package com.jishan.domain.entitiy

data class QuoteEntity(
    val quote: String,
    val author: String,
    val tags: List<String>,
    val category: String, // Consider removing this field if it's not necessary, as the API response does not include it
    val id: String,
)
