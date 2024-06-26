package com.book.manager.infrastructure.database.repository

import com.book.manager.domain.model.Book
import com.book.manager.domain.model.BookWithRental
import com.book.manager.domain.model.Rental
import com.book.manager.domain.repository.BookRepository
import com.book.manager.infrastructure.database.mapper.BookMapper
import com.book.manager.infrastructure.database.mapper.custom.BookWithRentalMapper
import com.book.manager.infrastructure.database.mapper.custom.select
import com.book.manager.infrastructure.database.mapper.custom.selectByPrimaryKey
import com.book.manager.infrastructure.database.mapper.deleteByPrimaryKey
import com.book.manager.infrastructure.database.mapper.insert
import com.book.manager.infrastructure.database.mapper.updateByPrimaryKeySelective
import com.book.manager.infrastructure.database.record.BookRecord
import com.book.manager.infrastructure.database.record.custom.BookWithRentalRecord
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class BookRepositoryImpl (
    private val bookWithRentalMapper: BookWithRentalMapper,
    private val bookMapper: BookMapper
): BookRepository {
    override fun findAllWithRental(): List<BookWithRental> {
        return bookWithRentalMapper.select().map{ toModel(it) }
    }

    private fun toModel(record: BookWithRentalRecord): BookWithRental {
        val book = Book(
            record.id!!,
            record.title!!,
            record.author!!,
            record.releaseDate!!
        )
        val rental = record.userId?.let {
            Rental(
                record.id!!,
                record.userId!!,
                record.rentalDatetime!!,
                record.returnDeadline!!
            )
        }
        return BookWithRental(book, rental)
    }

    override fun findWithRental(id: Long): BookWithRental? {
        return bookWithRentalMapper.selectByPrimaryKey(id)?.let { toModel(it) }
    }

    override fun register(book: Book) {
        bookMapper.insert(toRecord(book))
    }

    private fun toRecord(book: Book): BookRecord {
        return BookRecord(
            id = book.id,
            title = book.title,
            author = book.author,
            releaseDate = book.releaseDate
        )
    }

    override fun update(id: Long, title: String?, author: String?, releaseDate: LocalDate?) {
        bookMapper.updateByPrimaryKeySelective(
            BookRecord(
                id = id,
                title = title,
                author = author,
                releaseDate = releaseDate
            )
        )
    }

    override fun delete(id: Long) {
        bookMapper.deleteByPrimaryKey(id)
    }

}