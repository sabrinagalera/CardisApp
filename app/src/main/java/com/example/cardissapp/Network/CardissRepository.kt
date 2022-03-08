package com.example.cardissapp.Network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cardissapp.Models.DTOEmergencia
import com.example.cardissapp.Models.DTOEstadoVisita
import com.example.cardissapp.Models.DTOMovil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardissRepository {

    private var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun listarEmergencias(userData: DTOMovil) : MutableLiveData<List<DTOEmergencia>?> {
        val data = MutableLiveData<List<DTOEmergencia>??>()
        apiInterface?.listarEmergencias(userData)?.enqueue(object : Callback<List<DTOEmergencia>> {
            override fun onFailure(call: Call<List<DTOEmergencia>>, t: Throwable) {
                data.value = null
            }
            override fun onResponse(
                call: Call<List<DTOEmergencia>>,
                response: Response<List<DTOEmergencia>>
            ) {

                val res = response.body()
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }
        })
        return data
    }
     fun getDetalles(id:Int): MutableLiveData<DTOEmergencia?> {
         val data =MutableLiveData<DTOEmergencia?>()
         apiInterface?.getDetalles(id)?.enqueue(object: Callback<DTOEmergencia>{
             override fun onFailure(call: Call<DTOEmergencia>, t: Throwable) {
                 data.value = null
                 Log.e("errr",t.message.toString())
             }

             override fun onResponse(
                 call: Call<DTOEmergencia>,
                 response: Response<DTOEmergencia>
             ) {

                 val res = response.body()
                 if (response.code() == 200 &&  res!=null){
                     data.value = res
                 }else{
                     data.value = null
                 }

             }

         })
         return data
     }
    fun getHistorial(id:Int): MutableLiveData<List<DTOEmergencia>?>{
        val data =MutableLiveData<List<DTOEmergencia>?>()
        apiInterface?.getHistorial(id)?.enqueue(object: Callback<List<DTOEmergencia>>{
            override fun onFailure(call: Call<List<DTOEmergencia>>, t: Throwable) {
                Log.e("errr",t.message.toString())
                data.value = null
            }

            override fun onResponse(
                call: Call<List<DTOEmergencia>>,
                response: Response<List<DTOEmergencia>>
            ) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }else{
                    data.value = null
                }

            }

        })
        return data
    }

    fun postearEstado(userData: DTOEstadoVisita): MutableLiveData<DTOEmergencia?>{
        val data=MutableLiveData<DTOEmergencia?>()
        apiInterface?.postearEstado(userData)?.enqueue(object : Callback<DTOEmergencia>{

            override fun onFailure(call: Call<DTOEmergencia>, t: Throwable) {
                data.value=null
            }

            override fun onResponse(call: Call<DTOEmergencia>, response: Response<DTOEmergencia>) {
                val res = response.body()
                if(response.code() == 200 && res!=null){
                    data.value = res
                }else{
                    data.value=null
                }
            }

        })
        return data
    }


}




