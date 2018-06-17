package com.zenika.kbooks.feature.book

import java.time.LocalDate

/**
 * Book dto that will serialized and sent to clients.
 */
data class BookDto(var id: String? = null,
                   var title: String? = null,
                   var publication: LocalDate? = null,
                   var authorId: String? = null,
                   var authorName: String? = null)