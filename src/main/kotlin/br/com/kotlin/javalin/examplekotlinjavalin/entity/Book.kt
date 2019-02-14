package br.com.kotlin.javalin.examplekotlinjavalin.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Book(@Id @GeneratedValue(strategy = GenerationType.AUTO)
                val id: Long,
                val title: String,
                val description: String)