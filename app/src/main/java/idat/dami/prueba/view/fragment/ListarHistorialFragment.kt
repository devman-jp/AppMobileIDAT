package idat.dami.prueba.view.fragment

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import idat.dami.prueba.R
import idat.dami.prueba.databinding.FragmentListarHistorialBinding
import idat.dami.prueba.retrofit.AzSessionManager
import idat.dami.prueba.utilitarios.AppMensaje
import idat.dami.prueba.utilitarios.MiApp
import idat.dami.prueba.utilitarios.TipoMensaje
import idat.dami.prueba.view.adapters.HistorialAdapter
import idat.dami.prueba.viewmodel.HistorialViewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class ListarHistorialFragment : Fragment(), View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private val calendar = Calendar.getInstance();
    private val formatter = SimpleDateFormat("dd-MM-YYYY", Locale.ENGLISH)
    private var _binding : FragmentListarHistorialBinding? = null
    private val binding get() = _binding!!
    private lateinit var historialViewModel: HistorialViewModel
    private var sessionManager = AzSessionManager(MiApp.applicationContext)
    var token: String? = "";
    var fecha: String = "";

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
        binding.edtFecha.setOnClickListener{
            DatePickerDialog(
                requireActivity(),
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
        }
        binding.btnHistorial.setOnClickListener(this)
        return binding.root
    }

    fun listarHistorial(tokenAuth: String?){
        fecha = binding.edtFecha.text.toString();
        historialViewModel.listarHistorial(tokenAuth, fecha).observe(
            viewLifecycleOwner, Observer {
                    response -> run {
                        if(response?.size == null) {
                            AppMensaje.enviarMensaje(binding.root, "Ingrese una fecha válida", TipoMensaje.ERROR)
                        } else {
                            binding.rvHistorial.adapter = HistorialAdapter(response)
                        }
                }
            }
        )
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.btnHistorial -> {
                listarHistorial(token)
            }
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        calendar.set(year, month, day);
        displayFormattedDate(calendar.timeInMillis)
    }

    private fun displayFormattedDate(timestamp: Long) {
        binding.edtFecha.text = formatter.format(timestamp);
    }

}
