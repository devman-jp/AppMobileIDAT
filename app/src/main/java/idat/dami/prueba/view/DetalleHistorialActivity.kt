package idat.dami.prueba.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import idat.dami.prueba.R
import idat.dami.prueba.databinding.ActivityDetalleHistorialBinding
import idat.dami.prueba.retrofit.AzSessionManager
import idat.dami.prueba.utilitarios.MiApp
import idat.dami.prueba.viewmodel.EntregasViewModel

class DetalleHistorialActivity : AppCompatActivity() , View.OnClickListener{
    private lateinit var binding: ActivityDetalleHistorialBinding
    val Reques = 1
    private lateinit var entregasViewModel: EntregasViewModel
    private var sessionManager = AzSessionManager(MiApp.applicationContext)
    var token: String? = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleHistorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegrearHistorial.setOnClickListener(this)
        val x =intent.extras
        val remitente = x?.getString("REMITENTE")
        val consignado = x?.getString("CONSIGNADO")
        val direccion = x?.getString("DIRECCION")
        val distrito = x?.getString("DISTRITO")
        val telefono = x?.getString("TELEFONO")
        val codigocaja = x?.getInt("CODIGOCAJA")

        binding.txvremitenteDetalleHistorial.setText(""+remitente)
        binding.txvdireccionDealleHistorial.setText(""+direccion)
        binding.txvconsignadoDetalleHistorial.setText(""+consignado)
        binding.txvdistritoDetalleHistorial.setText(""+distrito)
        binding.txvtelefonoDetalleHistorial.setText(""+telefono)
        binding.txvcodigoDetalleHistorial.setText(""+codigocaja)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnRegrearHistorial -> {
                onBackPressed()
            }

        }
    }
}