package com.trainee.appinventiv.cryptocurrency.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "https://api.coinmarketcap.com/"
object ApiUtilities {

    fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}