package com.zenika.kbooks

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
@EnableReactiveMongoRepositories
class KbooksApplication

fun main(args: Array<String>) {
    runApplication<KbooksApplication>(*args)
}
