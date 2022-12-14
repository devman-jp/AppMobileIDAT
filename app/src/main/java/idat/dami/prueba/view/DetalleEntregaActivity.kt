package idat.dami.prueba.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import idat.dami.prueba.R
import idat.dami.prueba.databinding.ActivityDetalleEntregaBinding

class DetalleEntregaActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetalleEntregaBinding
    val Reques = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleEntregaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibTelefonoDE.setOnClickListener(this)
        binding.ibUbicacionDE.setOnClickListener(this)
        binding.btnEnviarAlerta.setOnClickListener(this)
        binding.ibSalirDE.setOnClickListener(this)

        val x =intent.extras
        val remitente = x?.getString("REMITENTE")
        val consignado = x?.getString("CONSIGNADO")
        val direccion = x?.getString("DIRECCION")
        val distrito = x?.getString("DISTRITO")
        val telefono = x?.getString("TELEFONO")
        val codigocaja = x?.getInt("CODIGOCAJA")

        binding.tvRemitenteDE.setText(""+remitente)
        binding.tvDireccionDE.setText(""+direccion)
        binding.tvConsignadoDE.setText(""+consignado)
        binding.tvDistritoDE.setText(""+distrito)
        binding.tvTelefonoDE.setText(""+telefono)
        binding.tvCodigoDE.setText(""+codigocaja)

    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.ibSalirDE -> {
                onBackPressed()
            }
            R.id.ibUbicacionDE ->{
                ubicacion()
            }
            R.id.ibTelefonoDE -> {
                if(ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this ,arrayOf(Manifest.permission.CALL_PHONE), Reques)
                }else{
                    llamar()
                }

            }
            R.id.btnEnviarAlerta ->{
                if (ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.SEND_SMS
                    )
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.SEND_SMS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.SEND_SMS),
                        1000
                    )
                } else {
                    mensaje()
                }


            }
        }

    }

    @SuppressLint("MissingPermission")
    private fun llamar() {
        val nrotelfono = binding.tvTelefonoDE.text.toString()
        val intentollamada = Intent(Intent.ACTION_CALL)

        intentollamada.data = Uri.parse("tel:" + nrotelfono)
        startActivity(intentollamada)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == Reques)llamar()
    }

    private fun ubicacion(){
        val ubicacion = binding.tvDireccionDE.text.toString() +
                " " + binding.tvDistritoDE.text.toString()
        val map = "http://maps.google.com/maps?q="+ubicacion
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(map))
        startActivity(i)
    }

    private fun mensaje(){
        val nrotelfono = binding.tvTelefonoDE.text.toString()
        val nombre = binding.tvConsignadoDE.text.toString()
        val mensajae = "HOLA ${nombre} TU PAQUETE ESTA EN CAMINO, AZCOURIER :)"
        try {
            val sms: SmsManager = SmsManager.getDefault()
            sms.sendTextMessage(nrotelfono, null, "$mensajae", null, null)
            Toast.makeText(applicationContext, "Mensaje Enviado.", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(
                applicationContext,
                "Mensaje no enviado, datos incorrectos.",
                Toast.LENGTH_LONG
            ).show()
            e.printStackTrace()
        }

    }

}