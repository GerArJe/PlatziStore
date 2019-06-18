package com.gerarje.platzistore.pojosretrofit

import com.google.gson.annotations.SerializedName

data class ResponseProducts(

    @field:SerializedName("payload")
    val payload: List<PayloadItem?>? = null,

    @field:SerializedName("statusCode")
    val statusCode: Int? = null
)