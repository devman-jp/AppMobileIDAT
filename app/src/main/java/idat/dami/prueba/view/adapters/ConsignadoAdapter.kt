package idat.dami.prueba.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import idat.dami.prueba.databinding.ItemConsignadoBinding
import idat.dami.prueba.retrofit.response.ResponseConsignado

class ConsignadoAdapter (private var lstconsignados: List<ResponseConsignado>)
    : RecyclerView.Adapter<ConsignadoAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemConsignadoBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemConsignadoBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(lstconsignados[position]){
                binding.tvid.text = idConsignado.toString()
                binding.tvnombre.text = nombre
                binding.tvapellido.text = apellido
                binding.tvtelefono.text = telefono
                binding.tvdni.text = dni
                binding.tvdireccion.text = direccion
                binding.tvdistrito.text = distrito
            }
        }

    }

    override fun getItemCount() = lstconsignados.size


    }