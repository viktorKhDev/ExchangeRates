package com.viktor.kh.dev.exchangerates.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.viktor.kh.dev.exchangerates.data.ExchangeRateRoom

@Dao
interface ExchangeRateDao {


    @Query("SELECT * FROM exchangerateroom")
    fun getAll(): List<ExchangeRateRoom>

    @Insert
    fun insert(exchangeRateRoom: ExchangeRateRoom)

    @Delete
    fun delete(exchangeRateRoom: ExchangeRateRoom)

}