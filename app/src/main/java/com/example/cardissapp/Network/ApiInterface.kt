package com.example.cardissapp.Network

import com.example.cardissapp.Models.*
import com.example.cardissapp.data.model.LoggedInUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @POST("loginCardiss")
    fun postlogin(@Body userData:LoggedInUser):Call<DTOMovil>

    @POST("listaEmergencias")
    fun listarEmergencias(@Body userData: DTOMovil):Call<List<DTOEmergencia>>

    @GET("DetalleVisita/{id}")
    fun getDetalles(@Path("id") id:Int): Call<DTOEmergencia>
}