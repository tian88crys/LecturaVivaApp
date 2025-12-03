package com.lecturaviva.app.data.repo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Modelo simple que usaremos en la UI
data class ExternalBook(
    val title: String,
    val author: String
)

// Respuesta parcial de OpenLibrary (solo lo que necesitamos)
data class OpenLibraryResponse(
    val docs: List<OpenLibraryDoc>
)

data class OpenLibraryDoc(
    val title: String?,
    val author_name: List<String>?
)

// Definimos la API externa
interface OpenLibraryService {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String
    ): OpenLibraryResponse
}

// Repositorio que usa Retrofit para consumir la API
class BookApiRepository {

    private val api: OpenLibraryService = Retrofit.Builder()
        .baseUrl("https://openlibrary.org/") // API externa
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OpenLibraryService::class.java)

    // Hace el GET y transforma la respuesta en una lista simple
    suspend fun searchBooks(query: String): List<ExternalBook> {
        val response = api.searchBooks(query)
        return response.docs.map {
            ExternalBook(
                title = it.title ?: "Sin título",
                author = it.author_name?.firstOrNull() ?: "Autor desconocido"
            )
        }
    }
}
