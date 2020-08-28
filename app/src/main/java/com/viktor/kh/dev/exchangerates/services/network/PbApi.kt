package com.viktor.kh.dev.exchangerates.services.network

import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import retrofit2.Call
import retrofit2.http.GET

interface PbApi {

    @GET("date=01.12.2019")
    fun  getPosts(): Call<CurrencyPojo>
}