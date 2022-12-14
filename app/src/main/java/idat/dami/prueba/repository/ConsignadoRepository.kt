package idat.dami.prueba.repository

import androidx.lifecycle.MutableLiveData
import idat.dami.prueba.retrofit.AzCourierCliente
import idat.dami.prueba.retrofit.response.ResponseConsignado
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsignadoRepository {

    var responseConsignado = MutableLiveData<List<ResponseConsignado>>()

    fun listarMascotas(): MutableLiveData<List<ResponseConsignado>> {
        val call: Call<List<ResponseConsignado>> =
            AzCourierCliente.retrofitService.listarConsignados()
        call.enqueue(object : Callback<List<ResponseConsignado>> {
            override fun onResponse(
                call: Call<List<ResponseConsignado>>,
                response: Response<List<ResponseConsignado>>
            ) {
                responseConsignado.value = response.body()
            }

            override fun onFailure(call: Call<List<ResponseConsignado>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return responseConsignado
    }

}