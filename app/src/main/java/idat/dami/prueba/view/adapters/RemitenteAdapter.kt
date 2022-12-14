package idat.dami.prueba.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import idat.dami.prueba.databinding.ItemConsignadoBinding
import idat.dami.prueba.databinding.ItemRemitenteBinding
import idat.dami.prueba.retrofit.response.ResponseRemitente

class RemitenteAdapter (private var listarRemitentes: List<ResponseRemitente>)
    : RecyclerView.Adapter<RemitenteAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRemitenteBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemitenteAdapter.ViewHolder {
        val binding = ItemRemitenteBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RemitenteAdapter.ViewHolder, position: Int) {
        with(holder){
            with(listarRemitentes[position]){

                binding.tvIdRemitente.text = idRemitente.toString()
                binding.tvNombreRemitente.text = nombre
                binding.tvApellidoRemitente.text = apellido
                binding.tvDniRemitente.text = dni
                binding.tvTelefonoRemitente.text = telefono

            }
        }

    }

    override fun getItemCount() = listarRemitentes.size

}
