package idat.dami.prueba.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import idat.dami.prueba.repository.AuthRepository
import idat.dami.prueba.retrofit.response.ResponseLogin

class AuthViewModel: ViewModel() {

    var responseLogin: LiveData<ResponseLogin>

    private var repository = AuthRepository()

    init {
        responseLogin = repository.loginResponse
    }

    fun autenticarUsuario(usuario: String, password: String) {
        responseLogin = repository.autenticarUsuario(usuario, password)
    }
}