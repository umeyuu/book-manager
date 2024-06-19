package com.book.manager.application.service

import com.book.manager.domain.model.Rental
import com.book.manager.domain.repository.BookRepository
import com.book.manager.domain.repository.RentalRepository
import com.book.manager.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

private const val RENTAL_TERM_DAYS = 14L

@Service
class RentalService (
    private val userRepository: UserRepository,
    private val rentalRepository: RentalRepository,
    private val bookRepository: BookRepository
){
    @Transactional
    fun startRental(bookId: Long, userId: Long){
        userRepository.find(userId) ?: throw RuntimeException("User not found. userId: $userId")
        val book = bookRepository.findWithRental(bookId) ?: throw RuntimeException("Book not found. bookId: $bookId")

        // 貸出中のチェック
        if (book.isRental) throw IllegalStateException("The book is already rented. bookId: $bookId")

        val rentalDateTime = LocalDateTime.now()
        val returnDeadline = rentalDateTime.plusDays(RENTAL_TERM_DAYS)
        val rental = Rental(
            bookId,
            userId,
            rentalDateTime,
            returnDeadline
        )

        rentalRepository.startRental(rental)
    }
}