package tech.johnnydev.calculaimc_kotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var etPeso: EditText
    private lateinit var etAltura: EditText

    private lateinit var tvResult: TextView

    private lateinit var btnCalcular: Button
    private lateinit var btnLimpar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etPeso = findViewById(R.id.etPeso)
        etAltura = findViewById(R.id.etAltura)

        tvResult = findViewById(R.id.tvResult)

        btnCalcular = findViewById(R.id.btCalcular)
        btnLimpar = findViewById(R.id.btLimpar)

        btnCalcular.setOnClickListener {
            btCalcularOnClick()
        }

        btnLimpar.setOnClickListener {
            btLimparOnClick()
        }
    }


    private fun btCalcularOnClick() {

        if ( etPeso.text.toString().isEmpty() ) {
            etPeso.error = getString( R.string.erro_peso )
            etPeso.requestFocus()
            return
        }


        if ( etAltura.text.toString().isEmpty() ) {
            etPeso.error = getString( R.string.erro_altura )
            etAltura.requestFocus()
            return
        }


        val imc = calculateBMI();

        tvResult.text = imc
    }

    private fun btLimparOnClick() {
        etPeso.text.clear()
        etAltura.text.clear()
        tvResult.text = getString((R.string.zeros))
        etPeso.requestFocus()
        Toast.makeText(this, (R.string.toast_cleanr_message), Toast.LENGTH_SHORT).show()
    }

    private fun  calculateBMI():String{
        return if(Locale.getDefault().language.equals( "en" )){
            BMIEN();
        }else {
            BMIPT();
        }
    }

    private fun  BMIEN() :String{
        val peso = etPeso.text.toString().toDouble()
        val altura = etAltura.text.toString().toDouble()
        val imc = 703 *(peso / altura.pow( 2.0))
        val numberFormat = NumberFormat.getInstance(Locale.US)
        return numberFormat.format(imc)
    }

    private fun BMIPT():String{
        val peso = etPeso.text.toString().toDouble()
        val altura = etAltura.text.toString().toDouble()
        val imc = peso / altura.pow( 2.0)
        val numberFormat = NumberFormat.getInstance(Locale.getDefault())
        return numberFormat.format(imc)
    }
}