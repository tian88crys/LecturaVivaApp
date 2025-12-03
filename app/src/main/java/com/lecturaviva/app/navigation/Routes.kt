package com.lecturaviva.app.navigation

object Routes {
    const val Splash   = "splash"
    const val Login    = "login"
    const val Home     = "home"
    const val Catalog  = "catalog"
    const val News     = "news"
    const val History  = "history"
    const val Profile  = "profile"

    const val Register = "register"

    // Detalle y Reserva con parámetro
    const val BookDetail = "book/{bookId}"
    const val Reserve    = "reserve/{bookId}"

    fun bookDetail(id: String) = "book/$id"
    fun reserve(id: String)    = "reserve/$id"
}
