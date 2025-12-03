package com.lecturaviva.app.data.repo

import com.google.firebase.firestore.FirebaseFirestore

data class User(
    val name: String = "",
    val email: String = "",
    val password: String = "" // solo para examen, no es seguro en producción
)

class UserRepository {

    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")

    fun registerUser(
        name: String,
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        val user = User(name = name, email = email, password = password)

        usersCollection
            .add(user)
            .addOnSuccessListener {
                onResult(true, null)
            }
            .addOnFailureListener { e ->
                onResult(false, e.message)
            }
    }

    fun login(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        usersCollection
            .whereEqualTo("email", email)
            .whereEqualTo("password", password)
            .get()
            .addOnSuccessListener { query ->
                if (!query.isEmpty) {
                    onResult(true, null)
                } else {
                    onResult(false, "Usuario o contraseña incorrectos")
                }
            }
            .addOnFailureListener { e ->
                onResult(false, e.message)
            }
    }
}
