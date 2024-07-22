package com.example.rehapp_20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class final_Modulo1: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_modulo1)

        val txt: Button = findViewById(R.id.buttonNuevoModulo)
        txt.setOnClickListener {

            val intent: Intent = Intent(this, MainActivity_modulo_menu:: class.java)
            startActivity(intent)




        }
    }
}