package idat.dami.prueba.repository

import androidx.lifecycle.MutableLiveData
import idat.dami.prueba.retrofit.AzCourierCliente
import idat.dami.prueba.retrofit.response.ResponseRepartidor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepartidorRepository {

    var responseRepartidor = MutableLiveData<ResponseRepartidor>();

    fun perfil(token: String?): MutableLiveData<ResponseRepartidor> {
        val call: Call<ResponseRepartidor> = AzCourierCliente.retrofitService.perfil("Bearer "+ token);
        call.enqueue(object : Callback<ResponseRepartidor> {
            override fun onResponse(
                call: Call<ResponseRepartidor>,
                response: Response<ResponseRepartidor>
            ) {
                responseRepartidor.value = response.body()
            }

            override fun onFailure(call: Call<ResponseRepartidor>, t: Throwable) {

            }

        })
        return responseRepartidor
    }
}