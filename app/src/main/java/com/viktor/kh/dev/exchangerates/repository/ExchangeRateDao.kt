package com.viktor.kh.dev.exchangerates.repository

import androidx.room.*
import com.viktor.kh.dev.exchangerates.data.ExchangeRateRoom

@Dao
interface ExchangeRateDao {


    @Query("SELECT * FROM exchangerateroom")
    fun getAll(): List<ExchangeRateRoom>

    @Query("SELECT * FROM exchangerateroom WHERE date = :date ")
    fun getForDate(date :String): List<ExchangeRateRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exchangeRateRoom: ExchangeRateRoom)

    @Delete
    fun delete(exchangeRateRoom: ExchangeRateRoom)


}