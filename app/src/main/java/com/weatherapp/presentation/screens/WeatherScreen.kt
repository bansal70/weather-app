package com.weatherapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.weatherapp.BuildConfig
import com.weatherapp.presentation.state.WeatherUIState
import com.weatherapp.data.model.WeatherResponse
import com.weatherapp.presentation.viewmodel.WeatherViewModel
import timber.log.Timber

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
  val searchQuery = remember { mutableStateOf("") }
  val weatherState by viewModel.searchQueryFlow.collectAsState()
  val currentCity by viewModel.currentCity.collectAsState(initial = null)
  val keyboard = LocalSoftwareKeyboardController.current

  LaunchedEffect(Unit) {
    viewModel.getCurrentCityWeather()
  }

  WeatherScreenContent(
    searchQuery = searchQuery,
    weatherState = weatherState,
    city = currentCity,
    onSearchCardClick = {
      searchQuery.value = ""
      viewModel.saveCity(it)
      keyboard?.hide()
    }
  )

  LaunchedEffect(searchQuery.value) {
    if (searchQuery.value.length >= 3) {
      viewModel.setSearchQuery(searchQuery.value)
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WeatherScreenContent(
  searchQuery: MutableState<String> = remember { mutableStateOf("") },
  weatherState: WeatherUIState = WeatherUIState.Initial,
  city: String? = "",
  onSearchCardClick: (WeatherResponse?) -> Unit = {},
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(top = 24.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top
  ) {
    // Search Bar
    WeatherSearchBar(searchQuery)

    Spacer(modifier = Modifier.height(16.dp))

    when (weatherState) {
      is WeatherUIState.Initial -> if (city.isNullOrEmpty()) EmptyCityScreen()
      is WeatherUIState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
      is WeatherUIState.Error -> ErrorMessage(weatherState.errorType)
      is WeatherUIState.Success -> WeatherUI(weatherState.result)
      is WeatherUIState.SearchResult -> WeatherSearchCard(
        weather = weatherState.result,
        onSearchCardClick = onSearchCardClick
      )
    }
  }
}