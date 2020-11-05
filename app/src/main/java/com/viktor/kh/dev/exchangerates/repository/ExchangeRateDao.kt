package com.viktor.kh.dev.exchangerates.repository

import androidx.room.*
import com.viktor.kh.dev.exchangerates.data.ExchangeRateRoom
import java.util.*

@Dao
interface ExchangeRateDao {


    @Query("SELECT * FROM exchangerateroom")
    suspend fun getAll(): List<ExchangeRateRoom>

    @Query("SELECT * FROM exchangerateroom WHERE currency = :currency ")
    suspend fun getForCurrency(currency :String):List<ExchangeRateRoom>


    @Insert
    suspend fun insert(exchangeRateRoom: ExchangeRateRoom)

    @Delete
    suspend fun delete(exchangeRateRoom: ExchangeRateRoom)


}