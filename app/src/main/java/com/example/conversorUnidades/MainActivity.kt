package com.example.conversorUnidades

import android.icu.text.DecimalFormat
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
                Snackbar.make(
                    binding.valorVenda,
                    "Selecione uma opção de venda.",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            //if para checagem se o campo de valor foi preenchido
            if (imtEmptyCheck?.isEmpty() == true) {
                Snackbar.make(
                    binding.valorVenda,
                    "Preencha o campo com o valor.",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                val edtValue: Float = imtEmptyCheck.toString().toFloat()
                // calculo da comissão
                if (binding.vendaDireta.isChecked) {
                    percentage = (edtValue * 0.45 / 100).toFloat()
                } else if (binding.showRoom.isChecked) {
                    percentage = (edtValue.toDouble() * 0.5f / 100).toFloat()
                } else {
                    binding.semiNovo.isChecked
                    percentage = (edtValue.toDouble() * 0.75f / 100).toFloat()
                }
                //resultado na tela
                binding.resultadoVenda.text = "A Comissão foi de %.2f".format(percentage)
                //limpando o campo de texto e radio buttons
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
}