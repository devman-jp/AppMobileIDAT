package idat.dami.prueba.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import idat.dami.prueba.databinding.ItemHistorialBinding
import idat.dami.prueba.retrofit.response.ResponseEntrega

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
                binding.tvNomApeConsignadoH.text = paquete.consignado.nombre.toString() + " " + paquete.consignado.apellido.toString()
                binding.tvNomApeRemitenteH.text = paquete.remitente.nombre.toString() + " " + paquete.remitente.apellido.toString()
                binding.tvDireccionRemitenteH.text = paquete.consignado.direccion.toString()

            }
        }

    }

    override fun getItemCount() = listarHistorial.size
}