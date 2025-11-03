package com.lecturaviva.app.ui.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lecturaviva.app.LecturaVivaApplication
import com.lecturaviva.app.data.model.Book
import com.lecturaviva.app.ui.components.TopBarWithHome

@Composable
fun BookDetailScreen(
    bookId: String,
    onReserve: () -> Unit,
    nav: NavHostController
) {
    val app = LocalContext.current.applicationContext as LecturaVivaApplication
    val bookRepository = app.container.bookRepository
    var book by remember { mutableStateOf<Book?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(bookId) {
        isLoading = true
        book = bookRepository.getBook(bookId)
        isLoading = false
    }

    Scaffold(topBar = { TopBarWithHome(title = "Detalle", nav = nav) }) { padding ->
        if (isLoading) {
            Box(Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (book == null) {
            Box(Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Libro no encontrado")
            }
        } else {
            Column(
                Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                book?.let { b ->
                    Text(b.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("Autor: ${b.author}", style = MaterialTheme.typography.bodyMedium)
                    Text(b.description, style = MaterialTheme.typography.bodyMedium)

                    Spacer(Modifier.weight(1f))

                    Button(
                        onClick = onReserve,
                        enabled = b.available > 0,
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Text(if (b.available > 0) "Reservar" else "Sin stock")
                    }
                }
            }
        }
    }
}
