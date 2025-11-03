package com.lecturaviva.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lecturaviva.app.LecturaVivaApplication
import com.lecturaviva.app.data.model.Reservation
import com.lecturaviva.app.data.model.ReservationStatus
import com.lecturaviva.app.ui.components.TopBarWithHome

@Composable
fun HistoryScreen(nav: NavHostController) {
    val app = LocalContext.current.applicationContext as LecturaVivaApplication
    val reservationRepository = app.container.reservationRepository

    var items by remember { mutableStateOf(reservationRepository.all()) }

    Scaffold(
        topBar = { TopBarWithHome(title = "Historial", nav = nav) },
    ) { padding ->
        if (items.isEmpty()) {
            Box(
                Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Aún no tienes reservas",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items, key = { it.id }) { r ->
                    ReservationCard(
                        reservation = r,
                        onCancel = {
                            reservationRepository.cancel(r.id)
                            items = reservationRepository.all()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ReservationCard(
    reservation: Reservation,
    onCancel: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            Modifier.padding(all = 14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = reservation.bookTitle,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                AssistChip(
                    onClick = {},
                    enabled = false,
                    label = { Text(statusLabel(reservation.status)) },
                    colors = AssistChipDefaults.assistChipColors(
                        disabledContainerColor = statusContainerColor(reservation.status),
                        disabledLabelColor = statusTextColor(reservation.status)
                    )
                )

                Text(
                    text = "Retiro: ${reservation.pickupDate} • Devolución: ${reservation.returnDate}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (reservation.status == ReservationStatus.PENDING) {
                OutlinedButton(
                    onClick = onCancel,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Cancel,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Cancelar reserva")
                }
            }
        }
    }
}

@Composable
private fun statusContainerColor(status: ReservationStatus) = when (status) {
    ReservationStatus.PENDING -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.25f)   // terracota suave
    ReservationStatus.PICKED_UP -> MaterialTheme.colorScheme.primary.copy(alpha = 0.22f)   // verde suave
    ReservationStatus.RETURNED -> MaterialTheme.colorScheme.surfaceVariant
    ReservationStatus.CANCELLED -> MaterialTheme.colorScheme.errorContainer
}

@Composable
private fun statusTextColor(status: ReservationStatus) = when (status) {
    ReservationStatus.PENDING -> MaterialTheme.colorScheme.onSecondary
    ReservationStatus.PICKED_UP -> MaterialTheme.colorScheme.onPrimary
    ReservationStatus.RETURNED -> MaterialTheme.colorScheme.onSurfaceVariant
    ReservationStatus.CANCELLED -> MaterialTheme.colorScheme.onErrorContainer
}

private fun statusLabel(status: ReservationStatus) = when (status) {
    ReservationStatus.PENDING -> "Pendiente"
    ReservationStatus.PICKED_UP -> "Retirada"
    ReservationStatus.RETURNED -> "Devuelta"
    ReservationStatus.CANCELLED -> "Cancelada"
}
