package idat.dami.prueba.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import idat.dami.prueba.databinding.FragmentListarEntregaBinding
import idat.dami.prueba.retrofit.AzSessionManager
import idat.dami.prueba.utilitarios.MiApp
import idat.dami.prueba.view.adapters.EntregasAdapter
import idat.dami.prueba.viewmodel.EntregasViewModel

class ListarEntregaFragment : Fragment() {



    private var _binding : FragmentListarEntregaBinding? = null
    private val  binding get() = _binding!!
    private lateinit var entregasViewModel: EntregasViewModel;
    private var sessionManager = AzSessionManager(MiApp.applicationContext)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListarEntregaBinding.inflate(inflater, container,false)
        binding.rvEntregas.layoutManager = LinearLayoutManager(requireActivity())
        entregasViewModel = ViewModelProvider(requireActivity()).get(EntregasViewModel::class.java)
        var token: String? = sessionManager.fetchAuthToken();
        listarEntregas(token)

        return binding.root
    }

    fun listarEntregas(token: String?){
        entregasViewModel.listarEntregas(token).observe(
            viewLifecycleOwner, Observer {
                    response -> binding.rvEntregas.adapter = EntregasAdapter(response)
            }
        )
    }

}