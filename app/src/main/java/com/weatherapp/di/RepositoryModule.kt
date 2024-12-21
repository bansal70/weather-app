package com.weatherapp.di

import com.weatherapp.data.api.WeatherApiService
import com.weatherapp.data.repository.WeatherRepositoryImpl
import com.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

  @Provides
  fun provideWeatherRepository(
      weatherApiService: WeatherApiService,
  ): WeatherRepository {
    return WeatherRepositoryImpl(weatherApiService)
  }
}
