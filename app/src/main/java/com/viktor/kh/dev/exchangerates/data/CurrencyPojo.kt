package com.viktor.kh.dev.exchangerates.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CurrencyPojo (
      @Expose
      @SerializedName("date") val date : String,
      @Expose
      @SerializedName("bank") val bank : String,
      @Expose
      @SerializedName("baseCurrency") val baseCurrency : Int,
      @Expose
      @SerializedName("baseCurrencyLit") val baseCurrencyLit : String,
      @Expose
      @SerializedName("exchangeRate") val exchangeRate : List<ExchangeRate>



)

