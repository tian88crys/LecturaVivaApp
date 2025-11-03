package com.lecturaviva.app

import com.lecturaviva.app.data.repo.BookRepository
import com.lecturaviva.app.data.repo.BookRepositoryImpl
import com.lecturaviva.app.data.repo.ReservationRepository
import com.lecturaviva.app.data.repo.ReservationRepositoryImpl

interface AppContainer {
    val bookRepository: BookRepository
    val reservationRepository: ReservationRepository
}

class DefaultAppContainer : AppContainer {
    override val bookRepository: BookRepository by lazy {
        BookRepositoryImpl()
    }
    override val reservationRepository: ReservationRepository by lazy {
        ReservationRepositoryImpl()
    }
}
