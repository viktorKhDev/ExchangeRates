package com.viktor.kh.dev.exchangerates.repository

import androidx.room.*
import com.viktor.kh.dev.exchangerates.data.ExchangeRateRoom

@Dao
interface ExchangeRateDao {


    @Query("SELECT * FROM exchangerateroom")
    suspend fun getAll(): List<ExchangeRateRoom>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exchangeRateRoom: ExchangeRateRoom)

    @Delete
    suspend fun delete(exchangeRateRoom: ExchangeRateRoom)


}