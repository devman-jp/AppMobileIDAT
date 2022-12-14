package idat.dami.prueba.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import idat.dami.prueba.repository.EntregasRepository
import idat.dami.prueba.retrofit.response.ResponseEntrega

class EntregasViewModel:  ViewModel(){

    var responseEntrega: LiveData<ResponseEntrega>
    private var repository = EntregasRepository()

    init {
        responseEntrega =repository.entregaResponse
    }

    fun listarEntregas(token: String?): LiveData<List<ResponseEntrega>> {
        return  repository.listarEntregas(token)
    }

    fun confirmarEntrega(token: String?, id: Int){
        responseEntrega = repository.confirmarEntregas(token, id)
    }

}