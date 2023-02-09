package com.inkrodriguez.consumeapi

import android.text.Editable
import com.inkrodriguez.consumeapi.api.myData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("json/{cep}")
    fun getData(@Path("cep")cep: Int): Call<myData>

}