package com.example.rehapp_20

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class citasCalendario : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    private var selectedDateTime: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citas_calendario)

        auth = FirebaseAuth.getInstance()

        val btnSelectDateTime: Button = findViewById(R.id.btn_select_date_time)
        val btnAgendarCita: Button = findViewById(R.id.btn_agendar_cita)
        val etComentarios: EditText = findViewById(R.id.et_comentarios)

        btnSelectDateTime.setOnClickListener {
            showDateTimePicker()
        }

        btnAgendarCita.setOnClickListener {
            val comentarios = etComentarios.text.toString()

            if (selectedDateTime != null) {
                agendarCita(selectedDateTime!!, comentarios)
            } else {
                Toast.makeText(this, "Selecciona una fecha y hora", Toast.LENGTH_SHORT).show()
            }
        }
}

    private fun showDateTimePicker() {
        val currentDate = Calendar.getInstance()
        val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            val timePicker = TimePickerDialog(this, { _, hourOfDay, minute ->
                selectedDateTime = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth, hourOfDay, minute)
                }
            }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true)
            timePicker.show()
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }

    private fun agendarCita(dateTime: Calendar, comentarios: String) {
        val userId = auth.currentUser?.uid ?: ""

        val cita = hashMapOf(
            "fecha" to dateTime.time,
            "user" to userId,
            "fisio" to "",
            "comentarios" to comentarios
        )

        db.collection("citas")
            .add(cita)
            .addOnSuccessListener {
                Toast.makeText(this, "Cita agendada con éxito", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al agendar cita: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}