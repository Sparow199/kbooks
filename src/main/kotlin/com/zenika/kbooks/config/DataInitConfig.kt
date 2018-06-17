package com.zenika.kbooks.config

import com.zenika.kbooks.feature.author.Author
import com.zenika.kbooks.feature.author.IAuthorRepository
import com.zenika.kbooks.feature.book.Book
import com.zenika.kbooks.feature.book.IBookRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux
import java.time.LocalDate

/**
 * Insert default data to repository.
 */
@Configuration
class DataInitConfig {

    private val log = LoggerFactory.getLogger(DataInitConfig::class.java)

    @Bean
    fun init(IAuthorRepository: IAuthorRepository, bookRepository: IBookRepository) = CommandLineRunner {

        log.info("Start database population ...")

        val stephenKing = Author(name = "Stephen King")

        val robinHobb = Author(name = "Robin Hobb")


        val book00 = Book(
                title = "22/11/63",
                publication = LocalDate.parse("2011-11-08"),
                author = stephenKing
        )

        val book01 = Book(
                title = "22/11/63",
                publication = LocalDate.parse("2011-11-08"),
                author = stephenKing
        )

        val book02 = Book(
                title = "L'assassin Royal",
                publication = LocalDate.parse("1998-12-17"),
                author = robinHobb
        )

        val book03 = Book(
                title = "L'assassin Royal",
                publication = LocalDate.parse("1998-12-17"),
                author = robinHobb
        )

        val book04 = Book(
                title = "L'assassin Royal",
                publication = LocalDate.parse("1998-12-17"),
                author = robinHobb
        )

        val book05 = Book(
                title = "L'assassin Royal",
                publication = LocalDate.parse("1998-12-17"),
                author = robinHobb
        )

        val book06 = Book(
                title = "L'assassin Royal",
                publication = LocalDate.parse("1998-12-17"),
                author = robinHobb
        )

        val book07 = Book(
                title = "L'assassin Royal",
                publication = LocalDate.parse("1998-12-17"),
                author = robinHobb
        )


        val book08 = Book(
                title = "L'assassin Royal",
                publication = LocalDate.parse("1998-12-17"),
                author = robinHobb
        )


        val book09 = Book(
                title = "L'assassin Royal10000000000000",
                publication = LocalDate.parse("1998-12-17"),
                author = robinHobb
        )


        val book10 = Book(
                title = "L'assassin Royal11111111111111",
                publication = LocalDate.parse("1998-12-17"),
                author = robinHobb
        )


        IAuthorRepository.deleteAll()
                .thenMany(
                        Flux.just(stephenKing, robinHobb)
                                .flatMap {
                                    IAuthorRepository.save(it)
                                }
                )
                .log()
                .subscribe(null, null) {
                    log.info("SUCCESS: Authors inserted to database")
                }

        bookRepository.deleteAll()
                .thenMany(
                        Flux.just(book00, book01, book02, book03, book04, book05, book06, book07, book08, book09, book10)
                                .flatMap {
                                    bookRepository.save(it)
                                }
                )
                .log()
                .subscribe(
                        null,
                        null
                ) {
                    log.info("SUCCESS: Books inserted to database")
                    log.info("End database population ...")
                }

    }
}