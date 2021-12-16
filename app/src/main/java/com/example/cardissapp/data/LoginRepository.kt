package com.example.cardissapp.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cardissapp.Models.DTOMovil
import com.example.cardissapp.Network.ApiClient
import com.example.cardissapp.Network.ApiInterface
import com.example.cardissapp.data.model.LoggedInUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository {

    private var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun getautorizacion(userData: LoggedInUser): MutableLiveData<DTOMovil?> {
        val data = MutableLiveData<DTOMovil?>()
        apiInterface?.postlogin(userData)?.enqueue(object : Callback<DTOMovil> {

            override fun onFailure(call: Call<DTOMovil>, t: Throwable) {
                //data.value = null
                Log.e("errr",t.message.toString())
            }

            override fun onResponse(
                call: Call<DTOMovil>,
                response: Response<DTOMovil>
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
}