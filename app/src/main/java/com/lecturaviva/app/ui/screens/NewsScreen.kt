package com.lecturaviva.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lecturaviva.app.ui.components.TopBarWithHome

// --- Modelo simple para la noticia ---
data class NewsItem(
    val id: String,
    val title: String,
    val description: String,
    val date: String,        // "YYYY-MM-DD"
    val category: String? = null
)

// --- Fuente mock (en memoria) ---
private fun sampleNews(): List<NewsItem> = listOf(
    NewsItem(
        id = "n1",
        title = "Club de lectura — Cortázar",
        description = "Este viernes nos reunimos para conversar sobre 'Rayuela'. Inscripciones en biblioteca.",
        date = "2025-11-05",
        category = "Actividades"
    ),
    NewsItem(
        id = "n2",
        title = "Nueva colección de Ciencia Ficción",
        description = "Llegaron títulos 2025 de Le Guin, Liu Cixin y N. K. Jemisin. ¡Reserva desde el catálogo!",
        date = "2025-11-01",
        category = "Novedades"
    ),
    NewsItem(
        id = "n3",
        title = "Charla literaria con autora invitada",
        description = "Conversatorio abierto sobre procesos creativos y edición. Cupos limitados.",
        date = "2025-11-10",
        category = "Eventos"
    )
)

// --- Pantalla de Novedades ---
@Composable
fun NewsScreen(nav: NavHostController) {
    val news = remember { sampleNews() }

    Scaffold(
        topBar = { TopBarWithHome(title = "Novedades", nav = nav) }
    ) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(padding)
        ) {
            items(news, key = { it.id }) { item ->
                NewsCard(item)
            }
        }
    }
}

@Composable
private fun NewsCard(item: NewsItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            // Categoría (opcional)
            item.category?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(Modifier.height(2.dp))
            }

            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = item.date,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
