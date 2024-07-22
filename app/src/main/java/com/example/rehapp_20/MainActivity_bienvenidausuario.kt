package com.example.rehapp_20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity_bienvenidausuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_bienvenidausuario)

        val txt: Button = findViewById(R.id.link_iniciar_sesion)
        txt.setOnClickListener {

            val intent: Intent = Intent(this, activity_bienvenida:: class.java)
            startActivity(intent)

        }
    }
}