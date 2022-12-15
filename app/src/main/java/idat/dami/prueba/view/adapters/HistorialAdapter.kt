package idat.dami.prueba.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import idat.dami.prueba.databinding.ItemHistorialBinding
import idat.dami.prueba.retrofit.response.ResponseEntrega
import idat.dami.prueba.view.DetalleEntregaActivity
import idat.dami.prueba.view.DetalleHistorialActivity

class HistorialAdapter (private var listarHistorial: List<ResponseEntrega>)
    : RecyclerView.Adapter<HistorialAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemHistorialBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistorialBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(listarHistorial[position]){
                binding.ivHistorial.drawable
                binding.tvNomApeConsignadoH.text = "Consignado: "+paquete.consignado.nombre.toString() + " " + paquete.consignado.apellido.toString()
                binding.tvNomApeRemitenteH.text = "Remitente: "+paquete.remitente.nombre.toString() + " " + paquete.remitente.apellido.toString()
                binding.tvDireccionRemitenteH.text = "Direccion: "+paquete.consignado.direccion.toString()
                binding.root.setOnClickListener{
                    val remitente = paquete.remitente.nombre +" "+ paquete.remitente.apellido
                    val consignado = paquete.consignado.nombre + " " + paquete.consignado.apellido
                    val direccion = paquete.consignado.direccion
                    val distrito = paquete.consignado.distrito
                    val telefono = paquete.consignado.telefono
                    val codigocaja = paquete.idPaquete
                    val codentrega = idEntrega
                    val context = binding.root.context
                    val intent = Intent(context, DetalleHistorialActivity::class.java)
                    intent.putExtra("REMITENTE",remitente)
                    intent.putExtra("DIRECCION", direccion)
                    intent.putExtra("CONSIGNADO", consignado)
                    intent.putExtra("DISTRITO", distrito)
                    intent.putExtra("TELEFONO", telefono)
                    intent.putExtra("CODIGOCAJA", codigocaja)
                    intent.putExtra("CODENTREGA", codentrega)
                    context.startActivity(intent)

                }

            }
        }

    }

    override fun getItemCount() = listarHistorial.size
}