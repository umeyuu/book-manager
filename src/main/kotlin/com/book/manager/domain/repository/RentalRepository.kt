package com.book.manager.domain.repository

import com.book.manager.domain.model.Rental
import org.springframework.data.annotation.Id

interface RentalRepository {
    fun startRental(rental: Rental)

    fun endRental(bookId: Long)
}