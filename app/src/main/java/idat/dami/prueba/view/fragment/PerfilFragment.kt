package idat.dami.prueba.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import idat.dami.prueba.R
import idat.dami.prueba.databinding.FragmentPerfilBinding
import idat.dami.prueba.retrofit.AzSessionManager
import idat.dami.prueba.utilitarios.MiApp
import idat.dami.prueba.view.LoginActivity
import idat.dami.prueba.viewmodel.RepartidorViewModel


class PerfilFragment : Fragment(), View.OnClickListener {


    private var _binding : FragmentPerfilBinding? = null
    private val  binding get() = _binding!!
    private lateinit var repartidorViewModel: RepartidorViewModel;
    private var sessionManager = AzSessionManager(MiApp.applicationContext)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPerfilBinding.inflate(inflater, container,false)
        repartidorViewModel = ViewModelProvider(requireActivity()).get(RepartidorViewModel::class.java)
        var token: String? = sessionManager.fetchAuthToken();
        fillPerfil(token)

        binding.bncerrarSession.setOnClickListener(this)




        return binding.root
    }

    fun fillPerfil(token: String?) {
        repartidorViewModel.perfilRepartidor(token).observe(
            viewLifecycleOwner, Observer {
                    response ->
                response?.let {
                    binding.tvNombreRepartidor.text = response.nombre + " " + response.apellido
                    binding.tvEmailRepartidor.text = response.correo
                    binding.tvTelefonoRepartidor.text = response.telefono
                }
            }
        )
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.bncerrarSession -> {
                cerrarSesion()

            }
        }
    }

    private fun cerrarSesion() {
        val cerrar = Intent(activity,LoginActivity::class.java)
        startActivity(cerrar)



    }



}