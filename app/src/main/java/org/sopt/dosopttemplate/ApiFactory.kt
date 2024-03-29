package org.sopt.dosopttemplate

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.dosopttemplate.api.AuthLogin
import org.sopt.dosopttemplate.api.AuthSignUp
import org.sopt.dosopttemplate.api.ReqresService
import retrofit2.Retrofit

object ApiFactory {
    private const val BASE_URL = BuildConfig.AUTH_BASE_URL


    private fun getLogOkHttpClient(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("Retrofit2", "CONNECTION INFO -> $message")
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(getLogOkHttpClient())
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ApiFactory2 {
    private const val BASE_URL2 = "https://reqres.in/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ServicePool {
    val authServiceLogin = ApiFactory.create<AuthLogin>()
    val authServiceSignup = ApiFactory.create<AuthSignUp>()
    val reqresService = ApiFactory2.create<ReqresService>()
}