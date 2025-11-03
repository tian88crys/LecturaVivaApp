package com.lecturaviva.app.data.repo

import com.lecturaviva.app.data.model.Book
import kotlinx.coroutines.delay
import java.util.UUID

class MockBookRepository : BookRepository {
    private val books = mutableListOf(
        Book(
            id = "1",
            title = "1984",
            author = "George Orwell",
            genre = "Distopía",
            description = "Clásico de control social",
            coverUrl = null,
            available = 3
        ),
        Book(
            id = "2",
            title = "Cien años de soledad",
            author = "G. García Márquez",
            genre = "Realismo mágico",
            description = "Saga de los Buendía",
            coverUrl = null,
            available = 0
        ),
        Book(
            id = "3",
            title = "Fahrenheit 451",
            author = "Ray Bradbury",
            genre = "Distopía",
            description = "Libros prohibidos",
            coverUrl = null,
            available = 5
        ),
        Book(
            id = "4",
            title = "El Principito",
            author = "A. de Saint-Exupéry",
            genre = "Fábula",
            description = "Mirada de niño",
            coverUrl = null,
            available = 2
        ),
    )

    override suspend fun getBooks(query: String?, genre: String?): List<Book> {
        delay(150)
        return books.filter { b ->
            (query.isNullOrBlank() || b.title.contains(query, true) || b.author.contains(query, true)) &&
                    (genre.isNullOrBlank() || b.genre.equals(genre, true))
        }
    }

    override suspend fun getBook(id: String): Book? {
        delay(120)
        return books.find { it.id == id }
    }

    override fun genres(): List<String> = books.map { it.genre }.distinct().sorted()

    override fun isAvailable(id: String): Boolean = books.find { it.id == id }?.available?.let { it > 0 } ?: false

    override fun consumeOne(id: String): Boolean {
        val idx = books.indexOfFirst { it.id == id }
        if (idx < 0) return false
        val current = books[idx]
        if (current.available <= 0) return false
        books[idx] = current.copy(available = current.available - 1)
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
        books.add(b)
        return b
    }
}
