package com.example.rehapp_20

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        // Inicializa Firebase Auth
        auth = FirebaseAuth.getInstance()

        val editTextEmail: EditText = findViewById(R.id.editTextEmailForgotPassword)
        val buttonResetPassword: Button = findViewById(R.id.buttonResetPassword)

        buttonResetPassword.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            if (email.isNotEmpty()) {
                if (isValidEmail(email)) {
                    resetPassword(email)
                } else {
                    Toast.makeText(this, "Por favor, ingresa un correo electrónico válido.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, ingresa tu correo electrónico.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun resetPassword(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("ForgotPassword", "Correo de restablecimiento enviado.")
                    Toast.makeText(this, "Correo de restablecimiento enviado.", Toast.LENGTH_SHORT).show()
                } else {
                    Log.w("ForgotPassword", "Error al enviar el correo: ${task.exception?.message}")
                    Toast.makeText(this, "Error al enviar el correo: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
