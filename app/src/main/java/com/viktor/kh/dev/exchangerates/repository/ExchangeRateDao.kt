package com.viktor.kh.dev.exchangerates.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExchangeRateDao {


    @Query("SELECT * FROM exchangeratedao")
    fun getAll(): List<ExchangeRateDao>

    @Insert
    fun insert(exchangeRateDao: ExchangeRateDao)

    @Delete
    fun delete(exchangeRateDao: ExchangeRateDao)

}