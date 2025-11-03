package com.lecturaviva.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.lecturaviva.app.navigation.AppNavHost
import com.lecturaviva.app.ui.theme.LecturaVivaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LecturaVivaTheme {
                val navController = rememberNavController()
                Scaffold { paddingValues ->
                    AppNavHost(nav = navController)
                }
            }
        }
    }
}
