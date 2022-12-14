package idat.dami.prueba.repository

import androidx.lifecycle.MutableLiveData
import idat.dami.prueba.retrofit.AzCourierCliente
import idat.dami.prueba.retrofit.response.ResponseConsignado
import idat.dami.prueba.retrofit.response.ResponseRemitente
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemitenteRepository {

    var responseRemitente = MutableLiveData<List<ResponseRemitente>>()

    fun listarRemitentes(): MutableLiveData<List<ResponseRemitente>> {
        val call: Call<List<ResponseRemitente>> =
            AzCourierCliente.retrofitService.listarRemitentes()
        call.enqueue(object : Callback<List<ResponseRemitente>> {

            override fun onResponse(
                call: Call<List<ResponseRemitente>>,
                response: Response<List<ResponseRemitente>>
            ) {
                responseRemitente.value = response.body()
            }

            override fun onFailure(call: Call<List<ResponseRemitente>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
        return responseRemitente
    }
}