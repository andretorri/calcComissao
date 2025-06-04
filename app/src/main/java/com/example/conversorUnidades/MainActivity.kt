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
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //associando os compeonentes do Edit Text
        val edtValue = findViewById<TextInputEditText>(R.id.valor_venda)
        //recuperar a ação do botão
        val calculate = findViewById<Button>(R.id.btn_calc)
        //associando cada Radio Button em variavel
        val vendaDireta = findViewById<RadioButton>(R.id.vendaDireta)
        val showRoom = findViewById<RadioButton>(R.id.showRoom)
        val semiNovo = findViewById<RadioButton>(R.id.semiNovo)
        val tipoVendas = findViewById<RadioGroup>(R.id.tipoVendas)

        //recuperar o valor digitado pelo usuário
        calculate.setOnClickListener {
            val edtValue: String = edtValue.text.toString()
            println(edtValue)
        }
        //verificar se radio button foi selecionado
        findViewById<RadioButton>(R.id.vendaDireta).setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("Radio", "Venda Direta $isChecked")
        }
    }
}