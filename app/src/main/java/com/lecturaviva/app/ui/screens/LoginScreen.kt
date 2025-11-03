package com.lecturaviva.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lecturaviva.app.R
import com.lecturaviva.app.ui.theme.ForestGreen
import com.lecturaviva.app.ui.theme.Terracotta
import com.lecturaviva.app.ui.theme.LecturaVivaTheme

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onRegister: () -> Unit = {}
) {
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    // Fondo beige ya viene del Theme; por si quieres reforzar:
    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ---- Logo tipográfico + subtítulo ----
            Text(
                "LeeConmigo !",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                "BIBLIOTECA DIGITAL",
                style = MaterialTheme.typography.bodyMedium.copy(
                    letterSpacing = 1.5.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f)
            )

            Spacer(Modifier.height(20.dp))

            // ---- Tarjeta: header verde + cuerpo terracota, con sombra y bordes suaves ----
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, shape = RoundedCornerShape(24.dp))
                    .clip(RoundedCornerShape(24.dp))
            ) {
                // Header verde
                Surface(color = ForestGreen, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Login",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp)
                    )
                }
                // Cuerpo terracota
                Surface(color = Terracotta, modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(18.dp)) {
                        OutlinedTextField(
                            value = user,
                            onValueChange = { user = it },
                            label = { Text("Usuario:") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(10.dp))
                        OutlinedTextField(
                            value = pass,
                            onValueChange = { pass = it },
                            label = { Text("Contraseña:") },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(10.dp))
                        TextButton(onClick = onRegister) {
                            Text(
                                "¿No tienes cuenta?",
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSecondary
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(10.dp))
            Icon(
                imageVector = Icons.Default.ArrowDownward,
                contentDescription = null,
                tint = ForestGreen
            )
            Spacer(Modifier.height(10.dp))

            // ---- Ilustración + CTA “Únete aquí” con elevación y bordes redondeados ----
            Card(
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    Modifier
                        .widthIn(max = 360.dp)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.login_illustration),
                        contentDescription = "Ilustración lectura",
                        modifier = Modifier
                            .height(160.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(Modifier.height(10.dp))
                    Button(
                        onClick = onRegister,
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = ForestGreen)
                    ) {
                        Text("Registrate Aquí", fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // ---- Botón Entrar a ancho completo, redondeado ----
            Button(
                onClick = onLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text("Entrar", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    LecturaVivaTheme(darkTheme = false) {
        LoginScreen(onLogin = {}, onRegister = {})
    }
}
