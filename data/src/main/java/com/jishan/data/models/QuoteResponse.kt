package com.jishan.data.models

import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("qotd_date") val qotdDate: String,
    val quote: Quote,
)

data class Quote(
    val id: Int,
    val body: String,
    val author: String,
    val tags: List<String>,
    val url: String,
    @SerializedName("favorites_count") val favoritesCount: Int,
    @SerializedName("upvotes_count") val upvotesCount: Int,
    @SerializedName("downvotes_count") val downvotesCount: Int,
    @SerializedName("author_permalink") val authorPermalink: String,
)
