package com.example.conversorUnidades

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
import com.google.android.material.textfield.TextInputEditText

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
        binding.btnCalc.setOnClickListener {
            val edtValue: String = binding.valorVenda.text.toString()
            println(edtValue)
        }
        //recuperar os Radio Buttons pelo Viewbinding
        binding.vendaDireta.setOnCheckedChangeListener { _, isChecked ->
            println("Check $isChecked")
        }
        binding.showRoom.setOnCheckedChangeListener { _, isChecked ->
            println("Check2 $isChecked")
        }
        binding.semiNovo.setOnCheckedChangeListener { _, isChecked ->
            println("Check3 $isChecked")
        }
    }
}