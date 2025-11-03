package com.lecturaviva.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.lecturaviva.app.ui.components.TopBarWithHome

@Composable
fun ProfileScreen(nav: NavHostController) {
    Scaffold(
        topBar = { TopBarWithHome(title = "Perfil", nav = nav) }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            // ... tu contenido
        }
    }
}
