package com.lecturaviva.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lecturaviva.app.ui.screens.*

@Composable
fun AppNavHost(nav: NavHostController) {
    NavHost(navController = nav, startDestination = Routes.Splash) {

        composable(Routes.Splash) {
            SplashScreen {
                nav.navigate(Routes.Login) {
                    popUpTo(Routes.Splash) { inclusive = true }
                    launchSingleTop = true
                }
            }
        }

        composable(Routes.Login) {
            LoginScreen(
                onLogin = {
                    nav.navigate(Routes.Home) {
                        popUpTo(Routes.Login) { inclusive = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onRegister = {
                    // 🔹 AHORA SÍ NAVEGA AL REGISTRO
                    nav.navigate(Routes.Register)
                }
            )
        }

        // 🔹 NUEVA PANTALLA DE REGISTRO
        composable(Routes.Register) {
            RegisterScreen(
                onRegistered = {
                    // después de registrarse volvemos al login
                    nav.navigate(Routes.Login) {
                        popUpTo(Routes.Register) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Home) { HomeScreen(nav = nav) }

        composable(Routes.Catalog) {
            CatalogScreen(
                onBookClick = { id -> nav.navigate(Routes.bookDetail(id)) },
                nav = nav
            )
        }

        composable(
            route = Routes.BookDetail,
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments?.getString("bookId").orEmpty()
            BookDetailScreen(
                bookId = id,
                onReserve = { nav.navigate(Routes.reserve(id)) },
                nav = nav
            )
        }

        composable(
            route = Routes.Reserve,
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments?.getString("bookId").orEmpty()
            ReserveScreen(bookId = id, nav = nav)
        }

        composable(Routes.News)    { NewsScreen(nav = nav) }
        composable(Routes.History) { HistoryScreen(nav = nav) }
        composable(Routes.Profile) { ProfileScreen(nav = nav) }
    }
}
