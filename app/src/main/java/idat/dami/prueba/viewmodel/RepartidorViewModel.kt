package idat.dami.prueba.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import idat.dami.prueba.repository.RepartidorRepository
import idat.dami.prueba.retrofit.response.ResponseRepartidor

class RepartidorViewModel: ViewModel() {

    private var repository = RepartidorRepository()

    fun perfilRepartidor(token: String?): MutableLiveData<ResponseRepartidor> {
        return repository.perfil(token);
    }
}