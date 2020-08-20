package com.viktor.kh.dev.exchangerates.services.network

import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import retrofit2.Call
import retrofit2.http.GET

interface NbuApi {

    @GET("NBUStatService/v1/statdirectory/exchange?json")
    fun  getPosts(): Call<List<CurrencyPojo>>
}