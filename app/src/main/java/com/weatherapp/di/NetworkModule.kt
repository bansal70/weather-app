package com.weatherapp.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.weatherapp.data.api.WeatherApiService
import com.weatherapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
    return loggingInterceptor
  }

  @Singleton
  @Provides
  fun provideHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
  ) = OkHttpClient
    .Builder()
    .addInterceptor(httpLoggingInterceptor)
    .readTimeout(30, TimeUnit.SECONDS)
    .connectTimeout(30, TimeUnit.SECONDS)
    .build()

  @Singleton
  @Provides
  fun provideConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create(
    Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .build()
  )

  @Singleton
  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory): Retrofit {
    return Retrofit.Builder()
      .baseUrl(Constants.BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(moshiConverterFactory)
      .build()
  }

  @Singleton
  @Provides
  fun provideApiService(retrofit: Retrofit): WeatherApiService = retrofit.create(WeatherApiService::class.java)
}
