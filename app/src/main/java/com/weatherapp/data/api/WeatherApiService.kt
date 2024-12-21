package com.weatherapp.data.api

import com.weatherapp.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
  @GET("v1/current.json")
  suspend fun getWeather(
    @Query("key") apiKey: String,
    @Query("q") city: String?,
  ): Response<WeatherResponse>
}
