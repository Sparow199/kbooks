package com.zenika.kbooks.feature.book

import com.zenika.kbooks.util.rest.IDtoConverter

/**
 * Convert book entity to book dto.
 */
object BookDtoConverter : IDtoConverter<Book, BookDto> {
    override fun convert(document: Book): BookDto =
        BookDto(id = document.id,
                title = document.title,
                publication = document.publication,
                authorId = document.author?.id,
                authorName = document.author?.name ?: "")
}