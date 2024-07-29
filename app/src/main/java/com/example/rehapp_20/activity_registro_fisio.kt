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

class activity_registro_fisio : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    // Initialize Firebase Firestore para la base de datos
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_fisio)

        // Inicia el proceso de Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Setup button listener for registration
        val buttonRegistrarFisioterapeuta: Button = findViewById(R.id.ButtonRegistrarFisioterapeuta)
        buttonRegistrarFisioterapeuta.setOnClickListener {
            val email = findViewById<EditText>(R.id.tex_Correo_fisio).text.toString()
            val password = findViewById<EditText>(R.id.text_contraseña_fisio).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.tex_comfirmar_contraseña_fisio).text.toString()
            val name = findViewById<EditText>(R.id.text_Nombre_fisio).text.toString()
            val identificacion = findViewById<EditText>(R.id.text_identificacion_fisio).text.toString()
            val tarjetaProfesional = findViewById<EditText>(R.id.tex_tajeta_fisio).text.toString()
            val contacto = findViewById<EditText>(R.id.text_telefono_fisio).text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword) {
                registerFisio(email, password, name, identificacion, tarjetaProfesional, contacto)
            } else {
                Toast.makeText(this, "Por favor, verifica los campos y asegúrate de que las contraseñas coincidan.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerFisio(email: String, password: String, name: String, identificacion: String, tarjetaProfesional: String, contacto: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration success
                    Log.d("reg_fisio", "createUserWithEmail:success")

                    // Obtener el ID del usuario recién creado
                    val userId = auth.currentUser?.uid ?: ""

                    // Crear un mapa con los datos del fisioterapeuta
                    val fisio = hashMapOf(
                        "correo" to email,
                        "nombre" to name,
                        "identificacion" to identificacion,
                        "tarjetaProfesional" to tarjetaProfesional,
                        "contacto" to contacto
                    )

                    // Guardar los datos del fisioterapeuta en Firestore
                    db.collection("fisio").document(userId)
                        .set(fisio)
                        .addOnSuccessListener {
                            Log.d("reg_fisio", "DocumentSnapshot successfully written!")

                            // Iniciar la actividad de registro exitoso
                            val intent = Intent(this, activity_encuesta1::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            Log.w("reg_fisio", "Error writing document", e)
                            Toast.makeText(this, "Fallo en el registro: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    // If registration fails, display a message to the user.
                    Log.w("reg_fisio", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, "Fallo en el registro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
