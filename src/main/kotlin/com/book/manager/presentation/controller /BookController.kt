package com.book.manager.presentation.controller

import com.book.manager.application.service.BookService
import com.book.manager.presentation.form.BookInfo
import com.book.manager.presentation.form.GetBookDetaliResponse
import com.book.manager.presentation.form.GetBookListResponse
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("book")
@CrossOrigin(origins = ["http://localhost:8081"], allowCredentials = "true")
class BookController (
    private val bookService: BookService
) {
    @GetMapping("/list")
    fun getList(): GetBookListResponse {
        val bookList = bookService.getList().map{
            BookInfo(it)
        }
        return GetBookListResponse(bookList)
    }

    @GetMapping("/detail/{book_id}")
    fun getDetail(@PathVariable("book_id") bookId: Long): GetBookDetaliResponse {
        val book = bookService.getDetail(bookId)
        return GetBookDetaliResponse(book)
    }
}