package com.lecturaviva.app.data.repo

import com.lecturaviva.app.data.model.Book

interface BookRepository {
    suspend fun getBooks(query: String?, genre: String?): List<Book>
    suspend fun getBook(id: String): Book?
    fun genres(): List<String>
    fun isAvailable(id: String): Boolean
    fun consumeOne(id: String): Boolean
    fun add(
        title: String,
        author: String,
        genre: String,
        description: String,
        coverUrl: String? = null,
        available: Int = 1
    ): Book
}
