package idat.dami.prueba.view.adapters


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import idat.dami.prueba.databinding.ItemEntregasBinding
import idat.dami.prueba.retrofit.response.ResponseEntrega
import idat.dami.prueba.view.DetalleEntregaActivity

class EntregasAdapter(private var lstentregas: List<ResponseEntrega>)
    : RecyclerView.Adapter<EntregasAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemEntregasBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEntregasBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(lstentregas[position]){
                binding.iventrega.drawable
                binding.tvnombresRemitente.text = paquete.remitente.nombre.toString()+" " + paquete.remitente.apellido.toString()
                binding.tvnombresConsignado.text = paquete.consignado.nombre.toString()+" " + paquete.consignado.apellido.toString()
                binding.tvdireccionConsignado.text = paquete.consignado.direccion.toString()
                binding.root.setOnClickListener{
                    val remitente = paquete.remitente.nombre +" "+ paquete.remitente.apellido
                    val consignado = paquete.consignado.nombre + " " + paquete.consignado.apellido
                    val direccion = paquete.consignado.direccion
                    val distrito = paquete.consignado.distrito
                    val telefono = paquete.consignado.telefono
                    val codigocaja = paquete.idPaquete
                    val context = binding.root.context
                    val intent = Intent(context, DetalleEntregaActivity::class.java)
                    intent.putExtra("REMITENTE",remitente)
                    intent.putExtra("DIRECCION", direccion)
                    intent.putExtra("CONSIGNADO", consignado)
                    intent.putExtra("DISTRITO", distrito)
                    intent.putExtra("TELEFONO", telefono)
                    intent.putExtra("CODIGOCAJA", codigocaja)
                    context.startActivity(intent)

                }
            }

        }

    }

    override fun getItemCount() = lstentregas.size
}