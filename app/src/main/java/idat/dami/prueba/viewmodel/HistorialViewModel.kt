package idat.dami.prueba.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import idat.dami.prueba.repository.HistorialRepository
import idat.dami.prueba.retrofit.response.ResponseEntrega

class HistorialViewModel: ViewModel() {

    private var repository = HistorialRepository()

    fun listarHistorial(fecha: String) : LiveData<List<ResponseEntrega>> {
        return repository.listarHistorial(fecha)
    }
}