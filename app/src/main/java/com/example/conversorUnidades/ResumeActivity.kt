package com.example.conversorUnidades

import android.icu.text.NumberFormat
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.conversorUnidades.databinding.ActivityResumeBinding
import java.util.Locale

class ResumeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResumeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val currencyFormatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResumeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val valorRecebido = intent.getStringExtra("valorVenda")?.toFloatOrNull()
        val percentage = intent.getFloatExtra("percentage", 0.0f)

        binding.valorNota.text = valorRecebido.toString()
        binding.valorComissao.text = "A Comissão foi de ${currencyFormatter.format(percentage)}"
        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}