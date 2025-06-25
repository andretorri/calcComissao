package com.example.conversorUnidades

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.conversorUnidades.databinding.ActivityMainBinding

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
            val emptyComissao = binding.valorPorcentagem.text
            //if para checagem se o radio button foi selecionado
            if (imtEmptyCheck?.isEmpty() == true || emptyComissao?.isEmpty() == true) {
                binding.layoutValorVenda.error = "Digite um valor numérico válido."
                binding.layoutValorPorcentagem.error = "Digite um valor numérico válido."
                return@setOnClickListener
            }
            //adição de try catch no lugar de checagem por IF
            try {
                val edtValue: Float = imtEmptyCheck.toString().toFloat()
                val edtComissao: Float = emptyComissao.toString().toFloat()
                val calc = edtValue * edtComissao / 100
                // cálculo da comissão
                percentage = calc
            } catch (e: NumberFormatException) {
                binding.layoutValorVenda.error = "Digite um valor numérico válido."
                binding.layoutValorPorcentagem.error = "Digite um valor numérico válido."
            }
            val intent = Intent(this, ResumeActivity::class.java)
            intent.apply {
                putExtra("valorVenda", binding.valorVenda.text.toString())
                putExtra("percentage", percentage)
                putExtra("valorPorcentagem", binding.valorPorcentagem.text.toString())
            }
            startActivity(intent)
            // limpar campos
            binding.valorVenda.text?.clear()
            binding.valorPorcentagem.text?.clear()
        }
    }
}