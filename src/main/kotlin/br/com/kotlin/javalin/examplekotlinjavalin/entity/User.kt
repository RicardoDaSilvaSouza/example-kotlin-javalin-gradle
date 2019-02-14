package br.com.kotlin.javalin.examplekotlinjavalin.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(@Id @GeneratedValue(strategy = GenerationType.AUTO)
                val id:Long,
                val name:String,
                val email:String)