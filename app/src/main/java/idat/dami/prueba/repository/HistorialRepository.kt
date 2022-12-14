package idat.dami.prueba.repository

import androidx.lifecycle.MutableLiveData
import idat.dami.prueba.retrofit.AzCourierCliente
import idat.dami.prueba.retrofit.response.ResponseEntrega
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistorialRepository {

    var responseEntrega = MutableLiveData<List<ResponseEntrega>>()

    fun listarHistorial(token: String?, fecha: String): MutableLiveData<List<ResponseEntrega>> {
        val call: Call<List<ResponseEntrega>> =
            AzCourierCliente.retrofitService.listarHistorial("Bearer " + token, fecha)
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
}