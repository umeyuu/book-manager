package com.book.manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class BookManagerApplication

fun main(args: Array<String>) {
	runApplication<BookManagerApplication>(*args)
//	val password = "user"
//	val encoder = BCryptPasswordEncoder()
//	val hashedPassword = encoder.encode(password)
//	println(hashedPassword)
}