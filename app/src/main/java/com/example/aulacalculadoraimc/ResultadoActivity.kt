package com.example.aulacalculadoraimc

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultadoActivity : AppCompatActivity() {

    private lateinit var textPeso: TextView
    private lateinit var textAltura: TextView
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)


        textPeso = findViewById(R.id.text_peso)
        textAltura = findViewById(R.id.text_altura)
        textResultado = findViewById(R.id.text_resultado)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
    }

    val bundle = intent.extras
        bundle?.let {
            val peso = it.getDouble("peso")
            val altura = it.getDouble("altura")

            textPeso.text = "Peso informado: $peso kg"
            textAltura.text = "Altura informada: $altura m"

            val imc = peso / (altura * altura)

            textResultado.text = when {
                imc < 18.5 -> "Baixo"
                imc in 18.5..24.9 -> "Normal"
                imc in 25.0..29.9 -> "Sobrepeso"
                imc in 30.0..39.9 -> "Obesidade"
                else -> "Obesidade Grave"
            }
        }
    }
}
