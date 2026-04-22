package com.example.prototipobanco

import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Bizum : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bizum)

        // Configuración de padding para diseño Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Referenciar los elementos de la interfaz
        val etDestinatario = findViewById<EditText>(R.id.etDestinatario)
        val etCantidad = findViewById<EditText>(R.id.etCantidad)
        val etConcepto = findViewById<EditText>(R.id.etConcepto)
        val btnConfirmar = findViewById<Button>(R.id.btnConfirmar)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        // 2. Configurar el campo de cantidad para que solo acepte números y decimales
        // Esto abrirá el teclado numérico y evitará letras.
        etCantidad.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

        // 3. Lógica del botón Confirmar
        btnConfirmar.setOnClickListener {
            val destinatario = etDestinatario.text.toString()
            val cantidad = etCantidad.text.toString()
            val concepto = etConcepto.text.toString()

            if (destinatario.isNotEmpty() && cantidad.isNotEmpty()) {
                // Aquí iría la lógica para procesar el Bizum
                Toast.makeText(this, "Bizum de $cantidad€ enviado a $destinatario", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, rellena los campos obligatorios", Toast.LENGTH_SHORT).show()
            }
        }

        // 4. Lógica del botón Volver
        btnVolver.setOnClickListener {
            finish() // Cierra la actividad actual y vuelve a la anterior
        }
    }
}