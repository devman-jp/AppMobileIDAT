package idat.dami.prueba.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import idat.dami.prueba.R
import idat.dami.prueba.databinding.FragmentListarConsignadosBinding
import idat.dami.prueba.view.adapters.ConsignadoAdapter
import idat.dami.prueba.viewmodel.ConsignadoViewModel


class ListarConsignadosFragment : Fragment() {



    private  var _binding : FragmentListarConsignadosBinding? = null
    private val  binding get() = _binding!!
    private lateinit var consignadoViewModel: ConsignadoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListarConsignadosBinding
            .inflate(inflater, container,false)
        binding.rvConsignados.layoutManager = LinearLayoutManager(
            requireActivity())
        consignadoViewModel = ViewModelProvider(requireActivity())
            .get(ConsignadoViewModel::class.java)
        listarConsignados()

        return binding.root
    }

    fun listarConsignados(){
        consignadoViewModel.listarConsignado().observe(
            viewLifecycleOwner, Observer {
                    response -> binding.rvConsignados.adapter = ConsignadoAdapter(response)
            }
        )
    }


}