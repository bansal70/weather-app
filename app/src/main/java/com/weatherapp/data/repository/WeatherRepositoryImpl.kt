package com.weatherapp.data.repository

import com.weatherapp.BuildConfig
import com.weatherapp.data.api.WeatherApiService
import com.weatherapp.domain.model.ErrorType
import com.weatherapp.domain.repository.WeatherRepository
import com.weatherapp.presentation.state.WeatherUIState
import com.weatherapp.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
  private val weatherApi: WeatherApiService,
) : WeatherRepository {

  override suspend fun getCityWeather(cityValue: Pair<String, Boolean>) = flow {
    emit(WeatherUIState.Loading)
    try {
      val response = weatherApi.getWeather(BuildConfig.WEATHER_API_KEY, cityValue.first)
      if (response.isSuccessful) {
        val weatherData = response.body()
        if (weatherData?.error != null) {
          emit(WeatherUIState.Error(ErrorType.EMPTY))
        } else {
          if (cityValue.second) {
            emit(WeatherUIState.Success(weatherData))
          } else {
            emit(WeatherUIState.SearchResult(weatherData))
          }
        }
      } else {
        val error = response.errorBody()?.toString()
        val apiError = error?.let {
          try {
            ErrorType.EMPTY
          } catch (e: Exception) {
            ErrorType.UNKNOWN_ERROR
          }
        } ?: ErrorType.UNKNOWN_ERROR

        emit(WeatherUIState.Error(apiError))
      }
    } catch (e: Exception) {
      emit(WeatherUIState.Error(ErrorType.NETWORK_ERROR))
    }
  }.flowOn(Dispatchers.IO)
}
