package com.jishan.domain.mappers

import com.jishan.data.models.Quote
import com.jishan.domain.entitiy.QuoteEntity

object QuoteMapper {
    fun toEntity(quote: Quote): QuoteEntity {
        return QuoteEntity(
            quote = quote.quote,
            author = quote.author,
            tags = quote.tags,
            category = quote.category,
            id = quote.id
        )
    }
}
