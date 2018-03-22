package com.zenika.kbooks.feature.book

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

/**
 * JPA Book entity.
 */
@Entity
@Table(name = "book")
data class Book(@Id @GeneratedValue var id: Long? = null,
                var title: String? = null,
                var publication: LocalDate? = null,
                var author: String? = null)