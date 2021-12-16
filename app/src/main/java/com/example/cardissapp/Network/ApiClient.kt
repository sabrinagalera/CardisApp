package com.example.cardissapp.Network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

const val BASEURL = "http://b88f0bed3a9e.sn.mynetname.net:2929/api/"

class ApiClient {


    companion object{

        private var retrofit: Retrofit?=null

        fun getApiClient(): Retrofit {
            /* val gson = GsonBuilder()
                 .setLenient()
                 .create()

             if (retrofit == null) {
                 retrofit = Retrofit.Builder()
                     .baseUrl(BASEURL)
                     .client(okHttpClient)
                     .addConverterFactory(GsonConverterFactory.create(gson))
                     .build()
             }*/
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)
                .build()
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create()) //important
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit!!
        }
    }

}
