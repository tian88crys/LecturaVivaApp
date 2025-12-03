package com.lecturaviva.app.data.repo

import com.google.firebase.firestore.FirebaseFirestore

// Modelo simple de usuario para el examen
data class User(
    val name: String = "",
    val email: String = "",
    val password: String = "" // ⚠️ solo para fines académicos (no seguro en producción)
)

class UserRepository {

    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")

    /**
     * CREATE: Registrar usuario en Firestore
     *
     * - Verifica si ya existe un usuario con el mismo correo
     * - Si no existe, lo crea
     */
    fun registerUser(
        name: String,
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        val normalizedEmail = email.trim()

        // 1) Revisar si el correo ya existe
        usersCollection
            .whereEqualTo("email", normalizedEmail)
            .get()
            .addOnSuccessListener { query ->
                if (!query.isEmpty) {
                    onResult(false, "El correo ya se encuentra registrado")
                } else {
                    // 2) Crear el usuario
                    val user = User(
                        name = name.trim(),
                        email = normalizedEmail,
                        password = password
                    )

                    usersCollection
                        .add(user)
                        .addOnSuccessListener {
                            onResult(true, null)
                        }
                        .addOnFailureListener { e ->
                            onResult(false, e.message ?: "Error al registrar usuario")
                        }
                }
            }
            .addOnFailureListener { e ->
                onResult(false, e.message ?: "Error al validar el correo")
            }
    }

    /**
     * READ: Login de usuario
     *
     * - Busca un documento con el mismo email + password
     * - Si encuentra al menos uno, login OK
     */
    fun login(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        val normalizedEmail = email.trim()

        usersCollection
            .whereEqualTo("email", normalizedEmail)
            .whereEqualTo("password", password)
            .get()
            .addOnSuccessListener { query ->
                val exists = !query.isEmpty
                if (exists) {
                    onResult(true, null)
                } else {
                    onResult(false, "Correo o contraseña incorrectos")
                }
            }
            .addOnFailureListener { e ->
                onResult(false, e.message ?: "Error al iniciar sesión")
            }
    }
}
