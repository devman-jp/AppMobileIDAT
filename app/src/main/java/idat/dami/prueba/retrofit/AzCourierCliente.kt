package idat.dami.prueba.retrofit

import idat.dami.prueba.utilitarios.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AzCourierCliente {

    private var okHttpClient = OkHttpClient
        .Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        //    .addInterceptor(ApiInterceptor())
        .build()

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl(Constantes().API_AZCOURIER_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: AzCourierServicio by lazy {
        buildRetrofit()
            .create(AzCourierServicio::class.java)
    }

}