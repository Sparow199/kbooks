package com.zenika.kbooks.feature.book

import com.zenika.kbooks.exception.BookNotFoundException
import com.zenika.kbooks.util.rest.PaginationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid

/**
 * Rest endpoint for books.
 */
@RestController
@RequestMapping("/book")
class BookResource {

    @Autowired
    private lateinit var bookService: BookService


    @GetMapping("/{id}")
    fun getBook(@PathVariable("id") id: String): Mono<ResponseEntity<BookDto>> = bookService.getBook(id)
            .map { savedBook ->
                ResponseEntity.ok(savedBook)
            }

    @GetMapping("")
    fun getBooks(@ModelAttribute pagination: PaginationDto): Flux<BookDto> = bookService.getBooksPaged(pagination)

    @PostMapping("")
    fun createBook(@Valid @RequestBody dto: BookDto): Mono<ResponseEntity<Book>> = bookService.createBook(dto)
            .map { createdBook ->
                ResponseEntity(createdBook, HttpStatus.OK)
            }

    @PutMapping("/{id}")
    fun updateBook(@PathVariable("id") id: String, @Valid @RequestBody dto: BookDto): Mono<ResponseEntity<Book>> = bookService.updateBook(id, dto)
            .map { updatedBook ->
                ResponseEntity(updatedBook, HttpStatus.OK)
            }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable("id") id: String): Mono<Void> = bookService.deleteBook(id)


}