package com.lirioams.payapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.lirioams.payapp.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Calendar
import java.util.Locale
import java.util.regex.Pattern
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val pago = getMonto()
        binding.tvMontoT.text = getString(R.string.montoValor, pago)

        //Lo que pasa al dar click en confirmar pago
        binding.btPagar.setOnClickListener{
            if(validarDatos()){
                val params = Bundle()

                params.putString("monto", pago)
                params.putString("name", binding.etNombre.text.toString())
                params.putString("cardNumber", binding.etnTarjeta.text.toString().removeRange(1,13))
                params.putString("email", binding.etCorreo.text.toString())

                val intent = Intent(this, Resultado::class.java)
                intent.putExtras(params)
                startActivity(intent)
            }
        }
    }

    fun getMonto(): String{
        val formatter = DecimalFormat("#,###.00", DecimalFormatSymbols(Locale.US))
        val rdm = (Random.nextFloat() * (5000.00 - 100.00) +100.00).toFloat()
        return formatter.format(rdm)
    }

    fun validarEmail(): Boolean{
        val email = binding.etCorreo.text.toString()
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
    fun validarDatos(): Boolean{

        // Validacion nombre
        if(binding.etNombre.text.isEmpty()){
            Toast.makeText(this, resources.getString(R.string.ingresa_nombre), Toast.LENGTH_SHORT).show()
            binding.etNombre.error = getString(R.string.valor_requerido)
            binding.etNombre.requestFocus()
            return false
        }

        //Validación tarjeta
        if(binding.etnTarjeta.text.isEmpty()){
            Toast.makeText(this, resources.getString(R.string.ingresa_tarjeta), Toast.LENGTH_SHORT).show()
            binding.etnTarjeta.error = getString(R.string.valor_requerido)
            binding.etnTarjeta.requestFocus()
            return false
        }
        if(binding.etnTarjeta.text.length < 16 || binding.etnTarjeta.text.length > 16) {
            Toast.makeText(this, resources.getString(R.string.l_tarjeta), Toast.LENGTH_SHORT).show()
            binding.etnTarjeta.error = getString(R.string.l_tarjeta_error)
            binding.etnTarjeta.requestFocus()
            return false
        }

        // Validación fecha de expiración
        var mes = 0
        var anio = 0

            //mes
        if(binding.etMes.text.isEmpty()){
            Toast.makeText(this, resources.getString(R.string.ingresa_mes), Toast.LENGTH_SHORT).show()
            binding.etMes.error = getString(R.string.valor_requerido)
            binding.etMes.requestFocus()
            return false
        }
        mes = Integer.parseInt(binding.etMes.text.toString())
        if(mes < 1 || mes > 12){
            Toast.makeText(this, resources.getString(R.string.l_mes), Toast.LENGTH_SHORT).show()
            binding.etMes.error = getString(R.string.l_mes_error)
            binding.etMes.requestFocus()
            return false
        }

            // Año
        if(binding.etAnio.text.isEmpty()){
            Toast.makeText(this, resources.getString(R.string.ingresa_anio), Toast.LENGTH_SHORT).show()
            binding.etAnio.error = getString(R.string.valor_requerido)
            binding.etAnio.requestFocus()
            return false
        }
        anio = Integer.parseInt(binding.etAnio.text.toString())
        if(anio > 40){
            Toast.makeText(this, resources.getString(R.string.l_anio), Toast.LENGTH_SHORT).show()
            binding.etAnio.error = getString(R.string.l_anio_error)
            binding.etAnio.requestFocus()
            return false
        }
            // Verificar que la tarjeta no esté expirada
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR) - 2000
        val month = calender.get(Calendar.MONTH) + 1

        if (month >= mes && year >= anio) {
            Toast.makeText(this, resources.getString(R.string.fExpiValida), Toast.LENGTH_SHORT).show()
            binding.etMes.error = getString(R.string.fecha_error)
            binding.etMes.requestFocus()
            return false
        }else{
            if (year > anio ) {
                Toast.makeText(this, resources.getString(R.string.fExpiValida), Toast.LENGTH_SHORT).show()
                binding.etAnio.error = getString(R.string.fecha_error)
                binding.etAnio.requestFocus()
                return false
            }
        }

        //Validación CVV
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

        //Validación correo
        if(binding.etCorreo.text.isEmpty()){
            Toast.makeText(this, resources.getString(R.string.ingresa_correo), Toast.LENGTH_SHORT).show()
            binding.etCorreo.error = getString(R.string.valor_requerido)
            binding.etCorreo.requestFocus()
            return false
        }
        if(!validarEmail()){
            Toast.makeText(this, resources.getString(R.string.formato_correo), Toast.LENGTH_SHORT).show()
            binding.etCorreo.error = getString(R.string.formato_correo_error)
            binding.etCorreo.requestFocus()
            return false
        }
        return true
    }
}