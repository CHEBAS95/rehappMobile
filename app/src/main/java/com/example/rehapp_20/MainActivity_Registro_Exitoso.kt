package com.example.rehapp_20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity_Registro_Exitoso : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_registro_exitoso)


        val txt: Button = findViewById(R.id.ButtonAvanzarEncuesta)
        txt.setOnClickListener {

            val intent: Intent = Intent(this, MainActivity_bienvenidausuario:: class.java)
            startActivity(intent)

        }


}
}