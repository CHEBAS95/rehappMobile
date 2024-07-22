package com.example.rehapp_20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class activity_encuesta1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta1)

        val txt: Button = findViewById(R.id.ButtonFinalizarEncuesta)
        txt.setOnClickListener {

            val intent: Intent = Intent(this, MainActivity_Registro_Exitoso:: class.java)
            startActivity(intent)




            }
    }
}