package br.com.kotlin.javalin.examplekotlinjavalin

import br.com.kotlin.javalin.examplekotlinjavalin.entity.User
import br.com.kotlin.javalin.examplekotlinjavalin.service.BookService
import br.com.kotlin.javalin.examplekotlinjavalin.service.UserService
import io.javalin.Javalin
import io.javalin.NotFoundResponse
import io.javalin.apibuilder.ApiBuilder.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@SpringBootApplication
class ExampleKotlinJavalinApplication {

	@Autowired
	lateinit var userService: UserService

	@Autowired
	lateinit var bookService: BookService

	@Value("\${javalin.server.port}")
	lateinit var port:String

	@Value("\${javalin.server.context}")
	lateinit var contextPath:String

	private val app = Javalin.create()
				.enableRouteOverview("routes")
				.enableCorsForAllOrigins()

	@PostConstruct
	private fun init() =
		app.port(port.toInt())
			.contextPath(contextPath)
			.routes {
				path("user") {
					get {it.json(userService.getAll())}
					get(":id") {ctx ->
						userService.getById(ctx.pathParam("id").toLong())?.let {
							ctx.json(it)
						} ?: throw NotFoundResponse()
					}
					post {it.json(userService.save(it.bodyAsClass(User::class.java)))}
					put {it.json(userService.save(it.bodyAsClass(User::class.java)))}
					delete {userService.delete(it.bodyAsClass(User::class.java))}
					delete(":id") { userService.delete(it.pathParam("id").toLong()) }
				}
				crud("book/:id", bookService)
			}.start()

	@PreDestroy
	private fun onDestroy() = app.stop()
}

fun main(args: Array<String>) {
	runApplication<ExampleKotlinJavalinApplication>(*args)
}