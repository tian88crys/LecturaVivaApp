package com.lecturaviva.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.lecturaviva.app.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithHome(
    title: String,
    nav: NavHostController,
    showHome: Boolean = true
) {
    CenterAlignedTopAppBar(
        title = { Text(title, style = MaterialTheme.typography.titleMedium) },
        navigationIcon = {
            if (showHome) {
                IconButton(
                    onClick = {
                        // Navegar al Home limpiando el back stack (inline)
                        nav.navigate(Routes.Home) {
                            popUpTo(nav.graph.startDestinationId) {
                                inclusive = false
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,   // Terracota
                        contentColor = MaterialTheme.colorScheme.onSecondary    // Blanco
                    )
                ) {
                    Icon(Icons.Filled.Home, contentDescription = "Volver al inicio")
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,      // Beige/card
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = Color.Unspecified
        )
    )
}
