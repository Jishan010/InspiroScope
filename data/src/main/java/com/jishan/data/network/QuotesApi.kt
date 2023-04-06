package com.jishan.data.network

import com.jishan.data.models.QuoteResponse
import retrofit2.Response
import retrofit2.http.GET

interface QuotesApi {
    @GET("qotd")
    suspend fun getRandomQuote(): Response<QuoteResponse>
}
