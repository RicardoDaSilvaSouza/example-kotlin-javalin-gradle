package br.com.kotlin.javalin.examplekotlinjavalin.service

import br.com.kotlin.javalin.examplekotlinjavalin.entity.Book
import br.com.kotlin.javalin.examplekotlinjavalin.data.BookRepository
import io.javalin.Context
import io.javalin.NotFoundResponse
import io.javalin.apibuilder.CrudHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookService: CrudHandler{

    @Autowired
    private lateinit var bookRepository: BookRepository

    override fun create(ctx: Context) {
        ctx.json(save(ctx.bodyAsClass(Book::class.java)))
    }

    override fun delete(ctx: Context, resourceId: String) =
        bookRepository.deleteById(resourceId.toLong())

    override fun getAll(ctx: Context) {
        ctx.json(bookRepository.findAll())
    }

    override fun getOne(ctx: Context, resourceId: String) {
        bookRepository.findById(resourceId.toLong())
                .ifPresentOrElse({ ctx.json(it) }, { throw NotFoundResponse() })
    }

    override fun update(ctx: Context, resourceId: String) {
        ctx.json(save(ctx.bodyAsClass(Book::class.java)))
    }

    private fun save(book:Book) = bookRepository.save(book)
}