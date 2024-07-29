package com.example.rehapp_20

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class activity_registro_usuario : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    // Initialize Firebase Firestore para la base de datos
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        // Inicia el proceso de Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Setup button listener for registration
        val buttonRegistrarme: Button = findViewById(R.id.ButtonRegistroExitoso)
        buttonRegistrarme.setOnClickListener {
            val name = findViewById<EditText>(R.id.textView_Nombre_usuario).text.toString()
            val email = findViewById<EditText>(R.id.textView_Correo_registro).text.toString()
            val password = findViewById<EditText>(R.id.textView_Contraseña_registro).text.toString()
            val age = findViewById<EditText>(R.id.age_user).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.textView_Confirmacion_registro).text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword) {
                registerUser(name, email, password, age)
            } else {
                Toast.makeText(this, "Por favor, verifica los campos y asegúrate de que las contraseñas coincidan.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(name: String, email: String, password: String, age: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration success
                    Log.d("reg_paciente", "createUserWithEmail:success")

                    // Obtener el ID del usuario recién creado
                    val userId = auth.currentUser?.uid ?: ""

                    // Crear un mapa con los datos del usuario
                    val user = hashMapOf(
                        "correo" to email,
                        "edad" to age,
                        "name" to name, // Puedes agregar un campo para el nombre del usuario si es necesario
                        "perfil" to "paciente" // Puedes agregar un campo para el perfil del usuario si es necesario
                    )

                    // Guardar los datos del usuario en Firestore
                    db.collection("users").document(userId)
                        .set(user)
                        .addOnSuccessListener {
                            Log.d("reg_paciente", "DocumentSnapshot successfully written!")

                            // Iniciar la actividad de registro exitoso
                            val intent = Intent(this, activity_encuesta1::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            Log.w("reg_paciente", "Error writing document", e)
                            Toast.makeText(this, "Fallo en el registro: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    // If registration fails, display a message to the user.
                    Log.w("reg_paciente", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, "Fallo en el registro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
