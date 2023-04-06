package com.jishan.domain.entitiy

data class QuoteEntity(
    val quote: String,
    val author: String,
    val tags: List<String>,
    val category: String,
    val id: String
)
