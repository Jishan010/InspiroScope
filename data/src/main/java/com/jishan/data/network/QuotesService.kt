package com.jishan.data.network

import com.jishan.data.models.QuoteResponse
import retrofit2.Response

class QuotesService(private val quotesApi: QuotesApi) {

    suspend fun getRandomQuote(): Response<QuoteResponse> {
        return quotesApi.getRandomQuote()
    }
}
