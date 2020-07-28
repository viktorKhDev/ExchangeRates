package com.viktor.kh.dev.exchangerates.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CurrencyPojo (
      @Expose
      @SerializedName
          ("r030") val r030 : Int,
      @Expose
      @SerializedName
            ("txt") val txt : String,
      @Expose
      @SerializedName
            ("rate") val rate : Double,
      @Expose
      @SerializedName
            ("cc") val cc : String,
      @Expose
      @SerializedName
            ("exchangedate") val exchangedate : String


)

