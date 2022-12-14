package idat.dami.prueba.repository


import android.util.Base64
import androidx.lifecycle.MutableLiveData
import idat.dami.prueba.retrofit.AzCourierCliente
import idat.dami.prueba.retrofit.response.ResponseLogin
import idat.dami.prueba.utilitarios.Constantes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {

    var loginResponse = MutableLiveData<ResponseLogin>()
    var username: String = Constantes().CLIENT_USER;
    var password: String = Constantes().CLIENT_PASS;
    var base: String = username + ":" + password;

    fun autenticarUsuario(username: String, password: String): MutableLiveData<ResponseLogin>{
        var authHeader = "Basic " + Base64.encodeToString(base.toByteArray(), Base64.NO_WRAP)
        val call: Call<ResponseLogin> = AzCourierCliente.retrofitService.login(authHeader, username, password, "password")
        call.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                loginResponse.value = response.body()
            }
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
            }
        })
        return loginResponse
    }
}