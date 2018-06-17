package com.zenika.kbooks.feature.author

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface IAuthorRepository : ReactiveMongoRepository<Author, String>