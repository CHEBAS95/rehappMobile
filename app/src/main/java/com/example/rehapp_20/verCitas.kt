package com.example.rehapp_20

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

class verCitas : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var citasAdapter: CitasAdapter
    private val citasList = mutableListOf<QueryDocumentSnapshot>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_citas)

        auth = FirebaseAuth.getInstance()
        recyclerView = findViewById(R.id.recyclerView_citas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        citasAdapter = CitasAdapter(citasList)
        recyclerView.adapter = citasAdapter

        loadCitas()
    }

    private fun loadCitas() {
        db.collection("citas")
            .whereEqualTo("fisio", "")
            .get()
            .addOnSuccessListener { documents ->
                citasList.clear()
                citasList.addAll(documents)
                citasAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al cargar citas: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    inner class CitasAdapter(private val citas: List<QueryDocumentSnapshot>) :
        RecyclerView.Adapter<CitasAdapter.CitaViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.intem_cita, parent, false)
            return CitaViewHolder(view)
        }

        override fun onBindViewHolder(holder: CitaViewHolder, position: Int) {
            val cita = citas[position]
            holder.bind(cita)
        }

        override fun getItemCount(): Int = citas.size

        inner class CitaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val fechaTextView: TextView = itemView.findViewById(R.id.tv_fecha)
            private val comentariosTextView: TextView = itemView.findViewById(R.id.tv_comentarios)
            private val aceptarButton: Button = itemView.findViewById(R.id.btn_aceptar)

            fun bind(cita: QueryDocumentSnapshot) {
                fechaTextView.text = cita.getTimestamp("fecha")?.toDate().toString()
                comentariosTextView.text = cita.getString("comentarios")
                aceptarButton.setOnClickListener {
                    aceptarCita(cita.id)
                }
            }
        }
    }

    private fun aceptarCita(citaId: String) {
        val fisioId = auth.currentUser?.uid ?: ""

        db.collection("citas").document(citaId)
            .update("fisio", fisioId)
            .addOnSuccessListener {
                Toast.makeText(this, "Cita aceptada", Toast.LENGTH_SHORT).show()
                loadCitas()  // Recargar la lista de citas
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al aceptar cita: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}