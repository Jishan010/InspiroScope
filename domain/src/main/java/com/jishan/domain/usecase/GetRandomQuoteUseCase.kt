package com.jishan.domain.usecase

import com.jishan.data.repository.QuotesRepository
import com.jishan.domain.entitiy.QuoteEntity
import com.jishan.domain.mappers.QuoteMapper

class GetRandomQuoteUseCase(private val quotesRepository: QuotesRepository) {
    suspend operator fun invoke(): Result<QuoteEntity> {
        val result = quotesRepository.getRandomQuote()
        return result.map(QuoteMapper::toEntity)
    }
}
