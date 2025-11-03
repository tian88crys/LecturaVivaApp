package com.lecturaviva.app.data.repo

import com.lecturaviva.app.data.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class BookRepositoryImpl : BookRepository {

    private val seed: MutableList<Book> = mutableListOf(
        Book(
            id = "1",
            title = "Cien años de soledad",
            author = "Gabriel García Márquez",
            genre = "Realismo mágico",
            description = "Saga de la familia Buendía en Macondo.",
            coverUrl = null,     // usa placeholder si es null
            available = 3
        ),
        Book(
            id = "2",
            title = "Rayuela",
            author = "Julio Cortázar",
            genre = "Ficción",
            description = "Una novela abierta que invita a saltar de capítulo.",
            coverUrl = null,
            available = 0
        ),
        Book(
            id = "3",
            title = "Ficciones",
            author = "Jorge Luis Borges",
            genre = "Cuento",
            description = "Clásicos relatos de laberintos, espejos y bibliotecas.",
            coverUrl = null,
            available = 5
        ),
        Book(
            id = "4",
            title = "El túnel",
            author = "Ernesto Sábato",
            genre = "Novela psicológica",
            description = "Un pintor narra una obsesión llevada al extremo.",
            coverUrl = null,
            available = 2
        )
    )

    override suspend fun getBooks(query: String?, genre: String?): List<Book> {
        return withContext(Dispatchers.IO) {
            val q = query?.trim()?.lowercase() ?: ""
            seed.filter { b ->
                val matchesText = q.isEmpty() ||
                        b.title.lowercase().contains(q) ||
                        b.author.lowercase().contains(q)
                val matchesGenre = genre.isNullOrBlank() || b.genre.equals(genre, ignoreCase = true)
                matchesText && matchesGenre
            }
        }
    }

    override suspend fun getBook(id: String): Book? {
        return withContext(Dispatchers.IO) {
            seed.find { it.id == id }
        }
    }

    override fun genres(): List<String> = seed.map { it.genre }.distinct().sorted()

    override fun isAvailable(id: String): Boolean = seed.find { it.id == id }?.available?.let { it > 0 } ?: false

    override fun consumeOne(id: String): Boolean {
        val idx = seed.indexOfFirst { it.id == id }
        if (idx < 0) return false
        val current = seed[idx]
        if (current.available <= 0) return false
        seed[idx] = current.copy(available = current.available - 1)
        return true
    }

    override fun add(
        title: String,
        author: String,
        genre: String,
        description: String,
        coverUrl: String?,
        available: Int
    ): Book {
        val b = Book(
            id = UUID.randomUUID().toString(),
            title = title,
            author = author,
            genre = genre,
            description = description,
            coverUrl = coverUrl,
            available = available
        )
        seed.add(b)
        return b
    }
}
