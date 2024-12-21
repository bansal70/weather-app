package com.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherapp.BuildConfig
import com.weatherapp.presentation.state.WeatherUIState
import com.weatherapp.data.datastore.DatastoreSettings
import com.weatherapp.domain.repository.WeatherRepository
import com.weatherapp.data.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class WeatherViewModel @Inject constructor(
  private val repository: WeatherRepository,
  private val datastoreSettings: DatastoreSettings
) : ViewModel() {

  private val _currentCity = MutableStateFlow<String?>(null)
  val currentCity: StateFlow<String?> = _currentCity

  private val _searchQueryState = MutableStateFlow(Pair("", false))

  fun setSearchQuery(query: String?, isSaved: Boolean = false) {
    _searchQueryState.value = Pair(query.orEmpty(), isSaved)
  }

  val searchQueryFlow: StateFlow<WeatherUIState> = _searchQueryState
    .debounce(300)
    .distinctUntilChanged()
    .flatMapLatest { query ->
      if (query.first.isEmpty()) {
        flowOf(WeatherUIState.Initial)
      } else {
        repository.getCityWeather(query)
      }
    }
    .stateIn(viewModelScope, SharingStarted.Lazily, WeatherUIState.Loading)

  fun saveCity(weatherData: WeatherResponse?) = viewModelScope.launch {
    weatherData?.location?.name?.let {
      datastoreSettings.updateCity(it)
      setSearchQuery(it, true)
    }
  }

  fun getCurrentCityWeather() = viewModelScope.launch {

    BuildConfig.WEATHER_API_KEY
    val savedCity = datastoreSettings.getSelectedCity.firstOrNull()
    if (!savedCity.isNullOrEmpty()) {
      _currentCity.emit(savedCity)
      setSearchQuery(savedCity, true)
    }
  }
}