package com.lecturaviva.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lecturaviva.app.ui.components.TopBarWithHome

// Modelo simple para datos del perfil
data class UserProfile(
    val name: String,
    val email: String,
    val joined: String,
    val booksReserved: Int,
    val booksReturned: Int
)

// Datos de ejemplo (mock)
fun sampleUser() = UserProfile(
    name = "Cristian Padilla",
    email = "cristian.padilla@lecturaviva.cl",
    joined = "Miembro desde marzo 2025",
    booksReserved = 5,
    booksReturned = 4
)

@Composable
private fun ProfileStat(label: String, value: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun ProfileScreen(nav: NavHostController) {
    val user = remember { sampleUser() }

    Scaffold(
        topBar = { TopBarWithHome(title = "Perfil", nav = nav) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Avatar
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = user.name.first().toString(),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Nombre y correo
            Text(
                text = user.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = user.email,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Text(
                text = user.joined,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Estadísticas de lectura
            Text(
                text = "Tu actividad lectora",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileStat("Reservas", user.booksReserved)
                ProfileStat("Devueltos", user.booksReturned)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de cierre de sesión (placeholder)
            Button(
                onClick = { /* aquí podrías volver al login o limpiar DataStore */ },
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = MaterialTheme.shapes.large
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}
