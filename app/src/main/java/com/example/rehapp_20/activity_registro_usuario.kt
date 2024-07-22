package com.example.rehapp_20

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class activity_registro_usuario : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        // Iiniciaa el proceso de  Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Setup button listener for registration
        val buttonRegistrarme: Button = findViewById(R.id.ButtonRegistroExitoso)
        buttonRegistrarme.setOnClickListener {
            val email = findViewById<EditText>(R.id.textView_Correo_registro).text.toString()
            val password = findViewById<EditText>(R.id.textView_Contraseña_registro).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.textView_Confirmacion_registro).text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword) {
                registerUser(email, password)
            } else {
                Toast.makeText(this, "Por favor, verifica los campos y asegúrate de que las contraseñas coincidan.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration success
                    Log.d("reg_paciente", "createUserWithEmail:success")

                    //inicio para la activity de regustro exitosoo
                    val intent = Intent(this,activity_encuesta1::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                } else {
                    // If registration fails, display a message to the user.
                    Log.w("reg_paciente", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, "Fallo en el registro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }


}
