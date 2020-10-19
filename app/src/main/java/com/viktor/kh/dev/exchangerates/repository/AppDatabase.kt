package com.viktor.kh.dev.exchangerates.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.viktor.kh.dev.exchangerates.data.ExchangeRateRoom

@Database(entities = arrayOf(ExchangeRateRoom::class), version = 1)
 abstract class AppDatabase :RoomDatabase() {
    abstract  fun exchangeRateDao(): ExchangeRateDao
}