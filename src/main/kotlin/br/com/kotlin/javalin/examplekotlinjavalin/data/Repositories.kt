package br.com.kotlin.javalin.examplekotlinjavalin.data

import br.com.kotlin.javalin.examplekotlinjavalin.entity.Book
import br.com.kotlin.javalin.examplekotlinjavalin.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>
interface BookRepository: JpaRepository<Book, Long>