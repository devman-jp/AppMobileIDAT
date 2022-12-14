package idat.dami.prueba.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import idat.dami.prueba.databinding.FragmentListarHistorialBinding
import idat.dami.prueba.retrofit.AzSessionManager
import idat.dami.prueba.utilitarios.MiApp
import idat.dami.prueba.view.adapters.HistorialAdapter
import idat.dami.prueba.viewmodel.HistorialViewModel

class ListarHistorialFragment : Fragment() {


    private var _binding : FragmentListarHistorialBinding? = null
    private val binding get() = _binding!!
    private lateinit var historialViewModel: HistorialViewModel
    private var sessionManager = AzSessionManager(MiApp.applicationContext)
    var token: String? = "";

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListarHistorialBinding
            .inflate(inflater,container, false)
        binding.rvHistorial.layoutManager = LinearLayoutManager(
            requireActivity())
        historialViewModel = ViewModelProvider(requireActivity())
            .get(HistorialViewModel::class.java)

        token = sessionManager.fetchAuthToken();
        listarHistorial(token)

        return binding.root
    }

    fun listarHistorial(tokenAuth: String?){
        historialViewModel.listarHistorial(tokenAuth,"30-11-2022").observe(
            viewLifecycleOwner, Observer {
                    response -> binding.rvHistorial.adapter = HistorialAdapter(response)
            }
        )
    }
}
