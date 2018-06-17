package com.zenika.kbooks.feature.book

import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux


/**
 * Repository to access books.
 */
@Repository
interface IBookRepository : ReactiveMongoRepository<Book, String> {

    @Query("{ id: { \$exists: true }}")
    fun retrieveAllQuotesPaged(page: Pageable): Flux<Book>

}