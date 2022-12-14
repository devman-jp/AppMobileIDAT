package idat.dami.prueba.repository

import androidx.lifecycle.MutableLiveData
import idat.dami.prueba.retrofit.AzCourierCliente
import idat.dami.prueba.retrofit.response.ResponseConsignado
import idat.dami.prueba.retrofit.response.ResponseEntrega
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntregasRepository {
    var responseEntrega = MutableLiveData<List<ResponseEntrega>>()
    var entregaResponse =MutableLiveData<ResponseEntrega>()

    fun listarEntregas(token: String?): MutableLiveData<List<ResponseEntrega>> {
        val call: Call<List<ResponseEntrega>> =
            AzCourierCliente.retrofitService.listarEntregas("Bearer "+ token)
        call.enqueue(object : Callback<List<ResponseEntrega>> {
            override fun onResponse(
                call: Call<List<ResponseEntrega>>,
                response: Response<List<ResponseEntrega>>
            ) {
                responseEntrega.value = response.body()
            }

            override fun onFailure(call: Call<List<ResponseEntrega>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return responseEntrega
    }

    fun confirmarEntregas(token: String?, idEntrega: Int):
            MutableLiveData<ResponseEntrega> {

        val call : Call<ResponseEntrega> = AzCourierCliente
            .retrofitService.confirmarEntregas("Bearer " + token, idEntrega)
        call.enqueue(object : Callback<ResponseEntrega>{
            override fun onResponse(
                call: Call<ResponseEntrega>,
                response: Response<ResponseEntrega>
            ) {
                entregaResponse.value = response.body()
            }

            override fun onFailure(call: Call<ResponseEntrega>, t: Throwable) {

            }
        })
        return entregaResponse
    }

}