package dev.grack.zmatchschedulefootbal.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
  companion object {
    fun getRetrofit(): Retrofit {
      return Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
  }
}