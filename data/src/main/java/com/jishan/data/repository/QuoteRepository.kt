package com.jishan.data.repository

import com.jishan.data.models.Quote

interface QuotesRepository {
    suspend fun getRandomQuote(): Result<Quote>
}
