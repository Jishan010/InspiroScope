package com.jishan.data.network

import com.jishan.data.models.QuoteResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuotesService {
    private val baseUrl = "https://quotes.rest/"

    private val quotesApi: QuotesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        quotesApi = retrofit.create(QuotesApi::class.java)
    }

    suspend fun getRandomQuote(): Response<QuoteResponse> {
        return quotesApi.getRandomQuote()
    }
}
