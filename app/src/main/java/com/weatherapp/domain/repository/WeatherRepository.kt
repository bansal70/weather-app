package com.weatherapp.domain.repository

import com.weatherapp.presentation.state.WeatherUIState
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
  suspend fun getCityWeather(cityValue: Pair<String, Boolean>): Flow<WeatherUIState>
}