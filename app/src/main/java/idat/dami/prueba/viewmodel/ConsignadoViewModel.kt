package idat.dami.prueba.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import idat.dami.prueba.repository.ConsignadoRepository
import idat.dami.prueba.retrofit.response.ResponseConsignado

class ConsignadoViewModel : ViewModel(){

    private var repository = ConsignadoRepository()

    fun listarConsignado(): LiveData<List<ResponseConsignado>> {
        return  repository.listarMascotas()
    }
}