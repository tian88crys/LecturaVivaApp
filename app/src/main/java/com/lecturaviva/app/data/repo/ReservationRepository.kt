package com.lecturaviva.app.data.repo

import com.lecturaviva.app.data.model.Reservation
import com.lecturaviva.app.data.model.ReservationStatus

interface ReservationRepository {
    fun all(): List<Reservation>
    fun create(reservation: Reservation)
    fun getById(id: String): Reservation?
    fun getByUser(email: String): List<Reservation>
    fun cancel(id: String)
    fun updateStatus(id: String, status: ReservationStatus)
    fun clear()
}
