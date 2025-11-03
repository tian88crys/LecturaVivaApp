package com.lecturaviva.app.data.repo

import com.lecturaviva.app.data.model.Reservation
import com.lecturaviva.app.data.model.ReservationStatus

class ReservationRepositoryImpl : ReservationRepository {

    private val reservations = mutableListOf<Reservation>()

    override fun all(): List<Reservation> = reservations.toList()

    override fun create(reservation: Reservation) {
        reservations.add(reservation)
    }

    override fun getById(id: String): Reservation? = reservations.find { it.id == id }

    override fun getByUser(email: String): List<Reservation> =
        reservations.filter { it.userEmail.equals(email, ignoreCase = true) }

    override fun cancel(id: String) {
        val index = reservations.indexOfFirst { it.id == id }
        if (index >= 0) {
            val r = reservations[index]
            reservations[index] = r.copy(status = ReservationStatus.CANCELLED)
        }
    }

    override fun updateStatus(id: String, status: ReservationStatus) {
        val index = reservations.indexOfFirst { it.id == id }
        if (index >= 0) {
            val r = reservations[index]
            reservations[index] = r.copy(status = status)
        }
    }

    override fun clear() {
        reservations.clear()
    }
}
