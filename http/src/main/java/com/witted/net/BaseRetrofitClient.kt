package com.witted.net

import android.util.Log
import com.jeremyliao.liveeventbus.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL
import java.util.concurrent.TimeUnit

abstract class BaseRetrofitClient {

    companion object CLIENT {
        private const val TIME_OUT = 5
    }

    private val client: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
//                .addInterceptor(getHttpLoggingInterceptor())
                .addInterceptor(getNetworkInterceptor())
                .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        handleBuilder(builder)
        builder.build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }
        return logging
    }

    abstract fun handleBuilder(builder: OkHttpClient.Builder)

    open fun <Service> getService(serviceClass: Class<Service>): Service {
        return Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://"+NetConfig.url)
                .build()
                .create(serviceClass)
    }


    //涉及到一些公用参数的拼接放在这里
    private fun getNetworkInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                var request = chain.request()
                val oldHost = request.url.host
                val oldPort = request.url.port
                try {
                    val ip = NetConfig.url
                    val url = URL("http://$ip")
                    val newHost = url.host
                    var newPort = if (url.port == -1) {
                        80
                    } else {
                        url.port
                    }
                    var reqBuilder: Request.Builder? = null
                    if (oldHost != newHost || oldPort != newPort) {
                        val httpUrl = request.url.newBuilder().host(newHost).port(newPort).build()
                        reqBuilder = request.newBuilder().url(httpUrl)
                    }
                    request = reqBuilder?.build() ?: request
                    return chain.proceed(request)
                } catch (e: Exception) {
                    Log.i("getNetworkInterceptor", "intercept: "+e.message)
                    e.printStackTrace()
                }
                return chain.proceed(request)
            }
        }
    }
}
