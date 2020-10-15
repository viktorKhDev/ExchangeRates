package com.viktor.kh.dev.exchangerates.repository

import androidx.room.Database

@Database(entities = arrayOf(ExchangeRateDao::class), version = 1)
 abstract class AppDatabase {
    abstract  fun exchangeratedao(): ExchangeRateDao
}