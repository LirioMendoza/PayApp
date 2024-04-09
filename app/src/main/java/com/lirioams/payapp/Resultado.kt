package com.lirioams.payapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.lirioams.payapp.databinding.ActivityResultadoBinding
import kotlin.random.Random

class Resultado : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultadoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val params = intent.extras

        if(params!=null){
            val name = params.getString("name")
            val cardNumber = params.getString("cardNumber")
            val email = params.getString("email")
            val monto = params.getString("monto")

            binding.tvMontoT.text = getString(R.string.montoValor, monto)
            binding.tvTarjetaT.text = getString(R.string.formatoTarjeta, cardNumber?.takeLast(4))
            binding.tvNombreT.text = name
            binding.tvEmailT.text = email

            if(exito()){
                binding.tvResultado.text = getText(R.string.exito)
                Toast.makeText(this, R.string.exito, Toast.LENGTH_LONG).show()
            }else{
                binding.tvResultado.text = getText(R.string.noExito)
                Toast.makeText(this, R.string.noExito, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun exito(): Boolean{
        return Random.nextDouble() < 0.75
    }
}