package com.jishan.data.datasource

import com.google.gson.Gson
import com.jishan.data.models.ErrorResponse
import com.jishan.data.models.Quote
import com.jishan.data.models.QuoteResponse
import com.jishan.data.network.QuotesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class QuotesRemoteDataSourceImpl(private val quotesService: QuotesService) : QuotesDataSource {

    override suspend fun getRandomQuote(): Result<Quote> = withContext(Dispatchers.IO) {
        try {
            val response = quotesService.getRandomQuote()
            if (response.isSuccessful) {
                val quote = response.body()?.quote
                if (quote != null) {
                    Result.success(quote)
                } else {
                    Result.failure(Exception("No quote found"))
                }
            } else {
                val errorResponse = parseErrorResponse(response)
                Result.failure(Exception(errorResponse?.error?.message ?: "Unknown error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun parseErrorResponse(response: Response<QuoteResponse>): ErrorResponse? {
        val gson = Gson()
        return try {
            gson.fromJson(response.errorBody()?.charStream(), ErrorResponse::class.java)
        } catch (e: Exception) {
            null
        }
    }
}
