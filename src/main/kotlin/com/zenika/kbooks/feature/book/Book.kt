package com.zenika.kbooks.feature.book

import com.zenika.kbooks.feature.author.Author
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

/**
 * JPA Book entity. All parameters have a default value because Hibernate need an empty constructor.
 */

@Document(collection = "book")
data class Book(@Id
                var id: String? = null,
                var title: String? = null,
                var publication: LocalDate? = null,
                var author: Author? = null)