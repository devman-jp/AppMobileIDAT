package idat.dami.prueba.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import idat.dami.prueba.repository.EntregasRepository
import idat.dami.prueba.retrofit.response.ResponseEntrega

class EntregasViewModel:  ViewModel(){

    private var repository = EntregasRepository()

    fun listarEntregas(token: String?): LiveData<List<ResponseEntrega>> {
        return  repository.listarEntregas(token)
    }
}