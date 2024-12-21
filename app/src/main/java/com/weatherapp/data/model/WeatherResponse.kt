package com.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
  @Json(name = "location") val location: Location? = Location(),
  @Json(name = "current") val current: Current? = Current(),
  @Json(name = "error") val error: ApiError? = null,
)

@JsonClass(generateAdapter = true)
data class Location(
  @Json(name = "name") val name: String? = "Chandigarh",
  @Json(name = "region") val region: String? = "Chandigarh",
  @Json(name = "country") val country: String? = "India",
  @Json(name = "lat") val latitude: Double? = 30.7372,
  @Json(name = "lon") val longitude: Double? = 76.7872,
  @Json(name = "tz_id") val timeZoneId: String? = "Asia/Kolkata",
  @Json(name = "localtime_epoch") val localtimeEpoch: Long? = 1734679897,
  @Json(name = "localtime") val localtime: String? = "2024-12-20 13:01",
)

@JsonClass(generateAdapter = true)
data class Current(
  @Json(name = "last_updated_epoch") val lastUpdatedEpoch: Long? = 1734679800,
  @Json(name = "last_updated") val lastUpdated: String? = "2024-12-20 13:00",
  @Json(name = "temp_c") val temperatureC: Double? = 22.2,
  @Json(name = "temp_f") val temperatureF: Double? = 72.0,
  @Json(name = "is_day") val isDay: Int? = 1,
  @Json(name = "condition") val condition: Condition? = Condition(),
  @Json(name = "wind_mph") val windMph: Double? = 5.8,
  @Json(name = "wind_kph") val windKph: Double? = 9.4,
  @Json(name = "wind_degree") val windDegree: Int? = 274,
  @Json(name = "wind_dir") val windDirection: String? = "W",
  @Json(name = "pressure_mb") val pressureMb: Double? = 1015.0,
  @Json(name = "pressure_in") val pressureIn: Double? = 29.96,
  @Json(name = "precip_mm") val precipitationMm: Double? = 0.0,
  @Json(name = "precip_in") val precipitationIn: Double? = 0.0,
  @Json(name = "humidity") val humidity: Int? = 19,
  @Json(name = "cloud") val cloud: Int? = 0,
  @Json(name = "feelslike_c") val feelsLikeC: Double? = 22.2,
  @Json(name = "feelslike_f") val feelsLikeF: Double? = 71.9,
  @Json(name = "vis_km") val visibilityKm: Double? = 10.0,
  @Json(name = "vis_miles") val visibilityMiles: Int? = 6,
  @Json(name = "uv") val uv: Double? = 2.4,
  @Json(name = "gust_mph") val gustMph: Double? = 6.7,
  @Json(name = "gust_kph") val gustKph: Double? = 10.8,
  @Json(name = "air_quality") val airQuality: AirQuality? = AirQuality(),
)

@JsonClass(generateAdapter = true)
data class Condition(
  @Json(name = "text") val text: String? = "Sunny",
  @Json(name = "icon") val icon: String? = "https://cdn.weatherapi.com/weather/64x64/day/113.png",
  @Json(name = "code") val code: Int? = 1000,
) {
  val weatherIcon by lazy { if (icon.orEmpty().startsWith("//")) "https:$icon" else icon.orEmpty() }
}

@JsonClass(generateAdapter = true)
data class AirQuality(
  @Json(name = "co") val co: Double? = 0.0,
  @Json(name = "no2") val no2: Double? = 0.0,
  @Json(name = "o3") val o3: Double? = 0.0,
  @Json(name = "so2") val so2: Double? = 0.0,
  @Json(name = "pm2_5") val pm25: Double? = 0.0,
  @Json(name = "pm10") val pm10: Double? = 0.0,
  @Json(name = "us-epa-index") val usEpaIndex: Int? = 1,
  @Json(name = "gb-defra-index") val gbDefraIndex: Int? = 1,
)

data class ApiError(
  val code: Int? = null,
  val message: String? = null,
)
