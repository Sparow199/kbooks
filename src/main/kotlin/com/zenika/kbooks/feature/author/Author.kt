package com.zenika.kbooks.feature.author

import com.zenika.kbooks.feature.book.Book
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * MongoDB Author document. All parameters have a default value.
 */
@Document(collection = "author")
data class Author(@Id
                  var id: String? = null,
                  var name: String? = null,
                  var books: List<Book> = mutableListOf())