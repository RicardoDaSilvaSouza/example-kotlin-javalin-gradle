package br.com.kotlin.javalin.examplekotlinjavalin.service

import br.com.kotlin.javalin.examplekotlinjavalin.entity.User
import br.com.kotlin.javalin.examplekotlinjavalin.data.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var userRepository:UserRepository

    fun getAll() = userRepository.findAll()
    fun getById(id:Long):User? = userRepository.findById(id).orElse(null)
    fun save(user: User) = userRepository.save(user)
    fun delete(user: User) = userRepository.delete(user)
    fun delete(id: Long) = userRepository.deleteById(id)
}