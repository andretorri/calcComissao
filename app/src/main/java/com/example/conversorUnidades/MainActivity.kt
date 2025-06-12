package com.example.conversorUnidades

import android.content.Intent
import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.conversorUnidades.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.math.BigDecimal
import java.util.Locale

class MainActivity : AppCompatActivity() {
    //criando a binding do ViewBinding para retirar o uso do FindViewById
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //recuperar o valor digitado pelo usuário
        //utilizando apenas o ViewBinding
        var percentage: Float = 0.0f
        binding.btnCalc.setOnClickListener {
            val imtEmptyCheck = binding.valorVenda.text
            //if para checagem se o radio button foi selecionado
            if (!binding.vendaDireta.isChecked && !binding.showRoom.isChecked && !binding.semiNovo.isChecked) {
                if (imtEmptyCheck?.isEmpty() == true) {
                    binding.layoutValorVenda.error = "Preencha o campo com o valor."
                    return@setOnClickListener
                } else {
                    binding.layoutValorVenda.error = null // limpa erro se tiver
                }
            }
            //adição de try catch no lugar de checagem por IF
            try {
                val edtValue: Float = imtEmptyCheck.toString().toFloat()
                // cálculo da comissão
                percentage = when {
                    binding.vendaDireta.isChecked -> edtValue * 0.45f / 100
                    binding.showRoom.isChecked -> edtValue * 0.5f / 100
                    else -> edtValue * 0.75f / 100
                }
            } catch (e: NumberFormatException) {
                binding.layoutValorVenda.error = "Digite um valor numérico válido."
            }
            val intent = Intent(this, ResumeActivity::class.java)
            intent.apply {
                putExtra("valorVenda", binding.valorVenda.text.toString())
                putExtra("percentage", percentage)
            }
            startActivity(intent)
            // limpar campos
            binding.valorVenda.text?.clear()
            binding.vendaDireta.isChecked = false
            binding.showRoom.isChecked = false
            binding.semiNovo.isChecked = false
        }
        //recuperar os Radio Buttons pelo Viewbinding
        binding.vendaDireta.setOnCheckedChangeListener { _, isChecked -> }
        binding.showRoom.setOnCheckedChangeListener { _, isChecked -> }
        binding.semiNovo.setOnCheckedChangeListener { _, isChecked -> }
    }
}