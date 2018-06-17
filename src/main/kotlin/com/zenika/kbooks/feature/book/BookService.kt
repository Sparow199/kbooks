package com.zenika.kbooks.feature.book

import com.zenika.kbooks.config.DataInitConfig
import com.zenika.kbooks.exception.AuthorNotFoundException
import com.zenika.kbooks.exception.BookNotFoundException
import com.zenika.kbooks.feature.author.Author
import com.zenika.kbooks.feature.author.IAuthorRepository
import com.zenika.kbooks.util.rest.PaginationDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux

/**
 * Book service class.
 */
@Service
class BookService {

    @Autowired
    private lateinit var authorRepository: IAuthorRepository
    @Autowired
    private lateinit var bookRepository: IBookRepository


    @Transactional(readOnly = true)
    fun getBook(id: String): Mono<BookDto> {
        // Find book in repository and throw exception if it was not found.
        return bookRepository.findById(id)
                .map { _book ->
                    BookDtoConverter.convert(_book)
                }
                .switchIfEmpty(Mono.error(BookNotFoundException("Book $id does not exist")))
                .log()
    }

    @Transactional(readOnly = true)
    fun getBooksPaged(pagination: PaginationDto): Flux<BookDto> {
        return bookRepository.retrieveAllQuotesPaged(pagination.toPageable())
                .map { page -> BookDtoConverter.convert(page) }
                .toFlux()
                .switchIfEmpty(Mono.error(BookNotFoundException("No books founds")))
                .log()
    }

    @Transactional
    fun createBook(dto: BookDto): Mono<Book> {

        val authorId = dto.authorId

        if (authorId == null) {
            // If author id is null throw exception.
            return Mono.error(AuthorNotFoundException("Author id must not be null"))
        } else {
            // Find author in database and throw exception if it does not exist.
            return authorRepository.findById(authorId)
                    .flatMap { author ->
                        // Create book.
                        val book = Book()
                        book.title = dto.title
                        book.publication = dto.publication
                        book.author = author
                        bookRepository.save(book)
                    }
                    .switchIfEmpty(Mono.error(AuthorNotFoundException("Author ${dto.authorId} does not exist")))
                    .log()
        }

    }

    @Transactional
    fun updateBook(id: String, dto: BookDto): Mono<Book> {
        // Look for book in database and throw exception if it was not found.

        val book = bookRepository.findById(id)
                .map { _book ->
                    // Update book.
                    _book.apply {
                        title = dto.title
                        publication = dto.publication
                    }
                }
                .filterWhen { _book ->
                    Mono.just(dto.authorId != null && dto.authorId == _book.author?.id)
                }
                .switchIfEmpty(Mono.error(BookNotFoundException("Book $id does not exist")))
                .log()

        val author = authorRepository.findById(dto.authorId!!)
                .switchIfEmpty(Mono.error(AuthorNotFoundException("Author $id does not exist")))
                .log()

        return Mono.zip<Book, Author, Book>(book, author) { _book, _author ->
            _book.author = _author
            _book
        }.flatMap { _book ->
            bookRepository.save(_book)
        }
                .switchIfEmpty(Mono.error(BookNotFoundException("Book $id does not exist, can't perform update")))
                .log()
    }

    @Transactional
    fun deleteBook(id: String): Mono<Void> {
        return bookRepository.findById(id)
                .flatMap { _book ->
                    bookRepository.delete(_book)
                }
                .switchIfEmpty(Mono.error(BookNotFoundException(" Book $id does not exist, can't perform deletion")))
                .log()

    }
}