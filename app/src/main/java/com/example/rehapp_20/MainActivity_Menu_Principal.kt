package com.example.rehapp_20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity_Menu_Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu_principal)

        val mod1: ImageView  = findViewById(R.id.imagen_modulo1)
        mod1.setOnClickListener {
            val intent: Intent = Intent(this, verCitas:: class.java)
            startActivity(intent)
        }

        val txt: ImageView  = findViewById(R.id.imagen_modulo2)
        txt.setOnClickListener {

            val intent: Intent = Intent(this, MainActivity_modulo_menu:: class.java)
            startActivity(intent)

        }

        val mod3: ImageView  = findViewById(R.id.imagen_modulo3)
        mod3.setOnClickListener {
            val intent: Intent = Intent(this, citasCalendario:: class.java)
            startActivity(intent)
        }

        val Concuenta: ImageView  = findViewById(R.id.imagen_modulo4)
        Concuenta.setOnClickListener {

            val intent: Intent = Intent(this, activity_inicio_sesion:: class.java)
            startActivity(intent)

        }
}
}
