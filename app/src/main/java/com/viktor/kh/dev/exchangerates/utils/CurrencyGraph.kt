package com.viktor.kh.dev.exchangerates.utils

data class CurrencyGraph (
    val date:Long,
    val value:Double
): Comparable<CurrencyGraph>{
    override fun compareTo(other: CurrencyGraph): Int {
        return if(other.date>date){
            1
        }else{
            0
        }
    }

}


