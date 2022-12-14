package idat.dami.prueba.retrofit

import idat.dami.prueba.retrofit.response.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface AzCourierServicio {


    @Headers("Accept: /")
    @POST("oauth/token")
    @FormUrlEncoded
    fun login(@Header("Authorization") authHeader: String,
              @Field("username") username: String,
              @Field("password") password: String,
              @Field("grant_type") grand_type: String): Call<ResponseLogin>

    @GET("v1/consignado/listar")
    fun listarConsignados(): Call<List<ResponseConsignado>>

    @GET("v1/repartidor/perfil")
    fun perfil(@Header("Authorization") token: String): Call<ResponseRepartidor>

    @GET("v1/entrega/listar")
    fun listarEntregas(@Header("Authorization") token: String): Call<List<ResponseEntrega>>

    @GET("v1/remitente/listar")
    fun listarRemitentes(): Call<List<ResponseRemitente>>

    @GET("v1/entrega/historial/{fecha}")
    fun listarHistorial(@Header("Authorization") token: String, @Path("fecha") fecha: String): Call<List<ResponseEntrega>>

    @POST("v1/entrega/confirmar/{id}")
    fun confirmarEntregas(@Header("Authorization") token: String, @Path("id") id: Int)
            :Call<ResponseEntrega>

}