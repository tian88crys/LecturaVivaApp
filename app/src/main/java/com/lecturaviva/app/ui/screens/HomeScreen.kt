package com.lecturaviva.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lecturaviva.app.navigation.Routes

@Composable
fun HomeScreen(nav: NavHostController) {
    Column(Modifier.padding(20.dp)) {
        Text("Inicio", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        Button(onClick = { nav.navigate(Routes.Catalog) }) { Text("Catálogo") }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { nav.navigate(Routes.News) }) { Text("Novedades") }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { nav.navigate(Routes.History) }) { Text("Historial") }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { nav.navigate(Routes.Profile) }) { Text("Perfil") }
    }
}
