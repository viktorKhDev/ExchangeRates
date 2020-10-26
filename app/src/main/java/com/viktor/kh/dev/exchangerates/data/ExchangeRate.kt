package com.viktor.kh.dev.exchangerates.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ExchangeRate (
    @Expose
    @SerializedName("baseCurrency") val baseCurrency : String?,
    @Expose
    @SerializedName("currency") val currency : String?,
    @Expose
    @SerializedName("saleRateNB") val saleRateNB : Double?,
    @Expose
    @SerializedName("purchaseRateNB") val purchaseRateNB : Double?,
    @Expose
    @SerializedName("saleRate") val saleRate : Double?,
    @Expose
    @SerializedName("purchaseRate") val purchaseRate : Double?
)
