package br.com.fatec.wave.mobile.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "https://wave-api-16zu.onrender.com/"

    // Adiciona o Bearer em todas as chamadas, menos login e cadastro
    private val cliente = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val req = chain.request()
            val caminho = req.url.encodedPath
            val publica = req.method == "POST" && (caminho == "/login" || caminho == "/usuarios")
            val token = Sessao.token
            if (token != null && !publica) {
                chain.proceed(req.newBuilder().header("Authorization", "Bearer $token").build())
            } else {
                chain.proceed(req)
            }
        }
        .build()

    val api: Api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(cliente)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
}
