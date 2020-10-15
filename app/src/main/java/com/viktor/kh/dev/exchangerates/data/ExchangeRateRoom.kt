package com.viktor.kh.dev.exchangerates.data

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class ExchangeRateRoom(
    @ColumnInfo(name = "date") val date : String,
    @ColumnInfo(name = "baseCurrency") val baseCurrency : String,
    @ColumnInfo(name = "currency") val currency : String,
    @ColumnInfo(name = "saleRateNB") val saleRateNB : Double,
    @ColumnInfo(name = "purchaseRateNB") val purchaseRateNB : Double,
    @ColumnInfo(name = "saleRate") val saleRate : Double,
    @ColumnInfo(name = "purchaseRate") val purchaseRate : Double
)