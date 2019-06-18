package com.gerarje.platzistore

import com.gerarje.platzistore.pojosretrofit.ResponseProducts
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET

interface Endpoints{
    @GET("list")
    fun getList(): Call<ResponseProducts>
}