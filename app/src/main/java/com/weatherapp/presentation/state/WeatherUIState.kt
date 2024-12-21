package com.weatherapp.presentation.state

import com.weatherapp.domain.model.ErrorType
import com.weatherapp.data.model.WeatherResponse

sealed class WeatherUIState {
  data object Initial : WeatherUIState()
  data class Error(val errorType: ErrorType) : WeatherUIState()
  data object Loading : WeatherUIState()
  data class Success(val result: WeatherResponse?) : WeatherUIState()
  data class SearchResult(val result: WeatherResponse?) : WeatherUIState()
}