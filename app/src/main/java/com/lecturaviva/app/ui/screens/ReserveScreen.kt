package com.lecturaviva.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lecturaviva.app.LecturaVivaApplication
import com.lecturaviva.app.data.model.Reservation
import com.lecturaviva.app.navigation.Routes
import com.lecturaviva.app.ui.components.TopBarWithHome
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReserveScreen(
    bookId: String,
    nav: NavHostController
) {
    val app = LocalContext.current.applicationContext as LecturaVivaApplication
    val bookRepository = app.container.bookRepository
    val reservationRepository = app.container.reservationRepository

    var book by remember { mutableStateOf<com.lecturaviva.app.data.model.Book?>(null) }
    LaunchedEffect(bookId) {
        book = bookRepository.getBook(bookId)
    }

    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var showPickupDialog by remember { mutableStateOf(false) }
    var showReturnDialog by remember { mutableStateOf(false) }
    var pickupDate by remember { mutableStateOf<LocalDate?>(null) }
    var returnDate by remember { mutableStateOf<LocalDate?>(null) }

    val today = remember { LocalDate.now() }

    val nameError   = name.trim().length < 2
    val emailError  = !email.contains("@") || !email.contains(".")
    val pickupError = pickupDate == null || pickupDate!!.isBefore(today)
    val returnError = returnDate == null || (pickupDate != null && !returnDate!!.isAfter(pickupDate))

    val formValid = !nameError && !emailError && !pickupError && !returnError && book != null

    Scaffold(
        topBar = { TopBarWithHome(title = "Reservar", nav = nav) },
        snackbarHost = { SnackbarHost(hostState = snackbar) }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = book?.title ?: "Libro", style = MaterialTheme.typography.titleMedium)

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre completo") },
                isError = nameError,
                supportingText = { if (nameError) Text("Ingresa tu nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo") },
                isError = emailError,
                supportingText = { if (emailError) Text("Correo inválido") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedButton(
                onClick = { showPickupDialog = true },
                modifier = Modifier.fillMaxWidth()
            ) { Text(pickupDate?.let { "Retiro: $it" } ?: "Seleccionar fecha de retiro") }

            OutlinedButton(
                onClick = { showReturnDialog = true },
                modifier = Modifier.fillMaxWidth()
            ) { Text(returnDate?.let { "Devolución: $it" } ?: "Seleccionar fecha de devolución") }

            if (showPickupDialog) {
                val state = rememberDatePickerState()
                DatePickerDialog(
                    onDismissRequest = { showPickupDialog = false },
                    confirmButton = {
                        TextButton(onClick = {
                            state.selectedDateMillis?.let { millis ->
                                pickupDate = millis.toLocalDate()
                                if (returnDate != null && !returnDate!!.isAfter(pickupDate)) {
                                    returnDate = null
                                }
                            }
                            showPickupDialog = false
                        }) { Text("OK") }
                    },
                    dismissButton = { TextButton(onClick = { showPickupDialog = false }) { Text("Cancelar") } }
                ) { DatePicker(state = state) }
            }

            if (showReturnDialog) {
                val state = rememberDatePickerState()
                DatePickerDialog(
                    onDismissRequest = { showReturnDialog = false },
                    confirmButton = {
                        TextButton(onClick = {
                            state.selectedDateMillis?.let { millis ->
                                returnDate = millis.toLocalDate()
                            }
                            showReturnDialog = false
                        }) { Text("OK") }
                    },
                    dismissButton = { TextButton(onClick = { showReturnDialog = false }) { Text("Cancelar") } }
                ) { DatePicker(state = state) }
            }

            if (pickupError) Text("La fecha de retiro debe ser hoy o posterior.", color = MaterialTheme.colorScheme.error)
            if (returnError) Text("La devolución debe ser posterior al retiro.", color = MaterialTheme.colorScheme.error)

            Spacer(Modifier.weight(1f))

            Button(
                onClick = {
                    if (formValid && book != null) {
                        reservationRepository.create(
                            Reservation(
                                bookId = book!!.id,
                                bookTitle = book!!.title,
                                userName = name.trim(),
                                userEmail = email.trim(),
                                pickupDate = pickupDate!!,
                                returnDate = returnDate!!
                            )
                        )
                        bookRepository.consumeOne(book!!.id)
                        scope.launch {
                            snackbar.showSnackbar("¡Reserva creada!")
                            nav.navigate(Routes.History) { launchSingleTop = true }
                        }
                    }
                },
                enabled = formValid,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Text("Confirmar reserva")
            }
        }
    }
}

private fun Long.toLocalDate(): LocalDate =
    Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDate()
