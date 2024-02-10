package com.jishan.domain.mappers

import com.jishan.data.models.Quote
import com.jishan.domain.entitiy.QuoteEntity

object QuoteMapper {
    fun toEntity(quote: Quote): QuoteEntity {
        return QuoteEntity(
            quote = quote.body,
            author = quote.author,
            tags = quote.tags,
            category = "", // The response does not include a category, so you can set it to an empty string or remove it from the QuoteEntity
            id = quote.id.toString(),
        )
    }
}
