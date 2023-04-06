package com.jishan.data.repository

import com.jishan.data.datasource.QuotesDataSource
import com.jishan.data.models.Quote

class QuotesRepositoryImpl(private val quotesDataSource: QuotesDataSource) : QuotesRepository {
    override suspend fun getRandomQuote(): Result<Quote> {
        return quotesDataSource.getRandomQuote()
    }
}
