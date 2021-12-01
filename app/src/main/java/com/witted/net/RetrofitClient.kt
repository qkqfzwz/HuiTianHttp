package com.witted.net

import com.witted.net.BaseRetrofitClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitClient : BaseRetrofitClient() {

    val service by lazy { getService(ApiService::class.java) }

    override fun handleBuilder(builder: OkHttpClient.Builder) {

        builder.addInterceptor(getHttpLoggingInterceptor())

    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BODY
//            logging.level = HttpLoggingInterceptor.Level.BASIC
        }
        return logging
    }

}


