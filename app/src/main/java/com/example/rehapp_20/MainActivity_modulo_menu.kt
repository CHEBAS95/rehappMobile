package com.example.rehapp_20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity_modulo_menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_modulo_menu)


        val txt: ImageView = findViewById(R.id.imagefoto_usuario)
        txt.setOnClickListener {

            val intent: Intent = Intent(this, modulo1:: class.java)
            startActivity(intent)

        }

    }
}
