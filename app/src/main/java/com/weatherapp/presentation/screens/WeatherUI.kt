package com.weatherapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.weatherapp.R
import com.weatherapp.data.model.WeatherResponse
import com.weatherapp.presentation.ui.theme.Black2C
import com.weatherapp.presentation.ui.theme.Gray9A
import com.weatherapp.presentation.ui.theme.GrayC4
import com.weatherapp.presentation.ui.theme.MediumFont
import com.weatherapp.presentation.ui.theme.SemiboldFont

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WeatherUI(weather: WeatherResponse? = WeatherResponse()) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(top = 64.dp, start = 16.dp, end = 16.dp),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Weather Image

    AsyncImage(
      model = "${weather?.current?.condition?.weatherIcon}",
      contentDescription = stringResource(R.string.weather_image),
      modifier = Modifier.size(123.dp)
    )

    Spacer(modifier = Modifier.height(24.dp))

    // City Name
    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = weather?.location?.name.orEmpty(),
        style = TextStyle(
          fontSize = 30.sp,
          fontFamily = SemiboldFont,
          fontWeight = FontWeight.SemiBold,
          color = Black2C
        )
      )
      Spacer(modifier = Modifier.width(11.dp))
      Icon(
        painter = painterResource(id = R.drawable.ic_navigation), // Replace with location icon
        contentDescription = stringResource(R.string.location_icon),
        tint = Black2C
      )
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Temperature
    Row(
      modifier = Modifier
        .wrapContentWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.Top
    ) {
      Text(
        text = "${weather?.current?.temperatureC}",
        style = TextStyle(
          fontSize = 70.sp,
          fontFamily = MediumFont,
          fontWeight = FontWeight.Medium,
          color = Black2C
        )
      )
      Image(
        painter = painterResource(id = R.drawable.ic_degree),
        contentDescription = stringResource(id = R.string.temperature),
        modifier = Modifier.padding(top = 15.dp, start = 12.dp)
      )
    }

    Spacer(modifier = Modifier.height(35.dp))

    // Humidity, UV Index, Feels Like
    Row(
      modifier = Modifier
        .wrapContentWidth()
        .background(
          color = Color(0xFFF2F2F2),
          shape = RoundedCornerShape(16.dp)
        )
        .padding(start = 16.dp, end = 28.dp, top = 16.dp, bottom = 16.dp),
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.CenterVertically
    ) {
      // Humidity
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
          text = stringResource(R.string.humidity),
          style = TextStyle(
            fontSize = 12.sp,
            fontFamily = MediumFont,
            fontWeight = FontWeight.Medium,
            color = GrayC4
          )
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
          text = "${weather?.current?.humidity}%",
          style = TextStyle(
            fontSize = 15.sp,
            fontFamily = MediumFont,
            fontWeight = FontWeight.Medium,
            color = Gray9A
          )
        )
      }

      Spacer(modifier = Modifier.width(56.dp))

      // UV Index
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
          text = stringResource(R.string.uv),
          style = TextStyle(
            fontSize = 12.sp,
            fontFamily = MediumFont,
            fontWeight = FontWeight.Medium,
            color = GrayC4
          )
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
          text = "${weather?.current?.uv}",
          style = TextStyle(
            fontSize = 15.sp,
            fontFamily = MediumFont,
            fontWeight = FontWeight.Bold,
            color = Gray9A
          )
        )
      }

      Spacer(modifier = Modifier.width(56.dp))

      // Feels Like
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
          text = stringResource(R.string.feels_like),
          style = TextStyle(
            fontSize = 12.sp,
            fontFamily = MediumFont,
            fontWeight = FontWeight.Medium,
            color = GrayC4
          )
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
          text = "${weather?.current?.feelsLikeC}°",
          style = TextStyle(
            fontSize = 15.sp,
            fontFamily = MediumFont,
            fontWeight = FontWeight.Medium,
            color = Gray9A
          )
        )
      }
    }
  }
}