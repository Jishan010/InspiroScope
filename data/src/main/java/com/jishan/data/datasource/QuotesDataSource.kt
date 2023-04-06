package com.jishan.data.datasource

import com.jishan.data.models.Quote

interface QuotesDataSource {
    suspend fun getRandomQuote(): Result<Quote>
}
