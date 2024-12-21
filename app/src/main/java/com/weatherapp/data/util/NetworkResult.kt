package com.weatherapp.data.util

sealed class NetworkResult<out T> {
  data object Loading : NetworkResult<Nothing>()
  data class Success<out T>(val data: T) : NetworkResult<T>()
  data class Error(val message: String?, val exception: Throwable? = null) : NetworkResult<Nothing>()
}
