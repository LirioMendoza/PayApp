package com.lirioams.payapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.lirioams.payapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        //Lo que pasa al dar click en confirmar pago

        binding.btPagar.setOnClickListener{
            if(validarDatos()){

            }
        }
    }

    fun validarDatos(): Boolean{
        if(binding.etNombre.text.isEmpty()){
            Toast.makeText(this, resources.getString(R.string.ingresa_nombre), Toast.LENGTH_SHORT).show()
            binding.etNombre.error = getString(R.string.valor_requerido)
            binding.etNombre.requestFocus()
            return false
        }
        if(binding.etnTarjeta.text.isEmpty()){
            Toast.makeText(this, resources.getString(R.string.ingresa_tarjeta), Toast.LENGTH_SHORT).show()
            binding.etnTarjeta.error = getString(R.string.valor_requerido)
            binding.etnTarjeta.requestFocus()
            return false
        }
        if(binding.etnTarjeta.text.length < 16 || binding.etnTarjeta.text.length > 16){
            Toast.makeText(this, resources.getString(R.string.l_tarjeta), Toast.LENGTH_SHORT).show()
            binding.etnTarjeta.error = getString(R.string.l_tarjeta_error)
            binding.etnTarjeta.requestFocus()
            return false
        }
        if(binding.etfExpi.text.isEmpty()){
            Toast.makeText(this, resources.getString(R.string.ingresa_fecha), Toast.LENGTH_SHORT).show()
            binding.etfExpi.error = getString(R.string.valor_requerido)
            binding.etfExpi.requestFocus()
            return false
        }
        if(binding.etCVV.text.isEmpty()){
            Toast.makeText(this, resources.getString(R.string.ingresa_CVV), Toast.LENGTH_SHORT).show()
            binding.etCVV.error = getString(R.string.valor_requerido)
            binding.etCVV.requestFocus()
            return false
        }
        if(binding.etCVV.text.length < 3 || binding.etCVV.text.length > 4){
            Toast.makeText(this, resources.getString(R.string.l_cvv), Toast.LENGTH_SHORT).show()
            binding.etCVV.error = getString(R.string.l_cvv_error)
            binding.etCVV.requestFocus()
            return false
        }
        if(binding.etCorreo.text.isEmpty()){
            Toast.makeText(this, resources.getString(R.string.ingresa_correo), Toast.LENGTH_SHORT).show()
            binding.etCorreo.error = getString(R.string.valor_requerido)
            binding.etCorreo.requestFocus()
            return false
        }


        return true
    }
}