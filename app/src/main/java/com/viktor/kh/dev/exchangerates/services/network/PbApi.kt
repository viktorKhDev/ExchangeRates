package com.viktor.kh.dev.exchangerates.services.network

import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PbApi {

    @GET("exchange_rates?json")
    fun  getPosts(@Query("date") date:String ): Call<CurrencyPojo>
}