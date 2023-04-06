package com.jishan.data.models

data class QuoteResponse(
    val success: Success,
    val contents: Contents
)

data class Success(
    val total: Int
)

data class Contents(
    val quotes: List<Quote>
)

data class Quote(
    val quote: String,
    val length: String,
    val author: String,
    val tags: List<String>,
    val category: String,
    val id: String
)
