package com.lecturaviva.app.data.model

import java.time.LocalDate
import java.util.UUID

data class Book(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val author: String,
    val genre: String,
    val coverUrl: String? = null,
    val description: String,
    val available: Int = 1
)

enum class ReservationStatus { PENDING, PICKED_UP, RETURNED, CANCELLED }

data class Reservation(
    val id: String = UUID.randomUUID().toString(),
    val bookId: String,
    val bookTitle: String,
    val userName: String,
    val userEmail: String,
    val pickupDate: LocalDate,
    val returnDate: LocalDate,
    var status: ReservationStatus = ReservationStatus.PENDING
)

data class Review(
    val id: String = UUID.randomUUID().toString(),
    val bookId: String,
    val userId: String,
    val rating: Int,
    val comment: String,
    val createdAt: Long = System.currentTimeMillis()
)

data class User(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val email: String
)
