package com.viktor.kh.dev.exchangerates.di

import com.viktor.kh.dev.exchangerates.services.network.NetworkService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class NetworkModule {

    private val BASE_URL = "https://api.privatbank.ua/p24api/exchange_rates?json&"

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
      return Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()
    }

    @Provides
    fun provideNetworkService(retrofit: Retrofit):NetworkService{
        return NetworkService(retrofit)
    }

}


