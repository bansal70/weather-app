package com.weatherapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.weatherapp.presentation.ui.theme.GrayF2
import com.weatherapp.presentation.ui.theme.MediumFont
import com.weatherapp.presentation.ui.theme.SemiboldFont

@Preview(showBackground = true)
@Composable
fun WeatherSearchCard(
  weather: WeatherResponse? = WeatherResponse(),
  onSearchCardClick: (WeatherResponse?) -> Unit = {},
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 20.dp, end = 19.dp, top = 16.dp)
      .clip(RoundedCornerShape(16.dp))
      .background(color = GrayF2)
      .clickable { onSearchCardClick(weather) },
    contentAlignment = Alignment.Center,
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 31.dp, vertical = 16.dp)
    ) {
      // City Name and Temperature
      Column {
        Text(
          text = weather?.location?.name.orEmpty(),
          style = TextStyle(
            fontSize = 20.sp,
            fontFamily = SemiboldFont,
            fontWeight = FontWeight.SemiBold,
            color = Black2C
          )
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
          modifier = Modifier
            .wrapContentWidth(),
          horizontalArrangement = Arrangement.SpaceEvenly,
          verticalAlignment = Alignment.Top
        ) {
          Text(
            text = "${weather?.current?.temperatureC}",
            style = TextStyle(
              fontSize = 60.sp,
              fontFamily = MediumFont,
              fontWeight = FontWeight.Medium,
              color = Black2C
            )
          )
          Image(
            painter = painterResource(id = R.drawable.ic_degree),
            contentDescription = stringResource(R.string.temperature),
            modifier = Modifier.padding(top = 15.dp, start = 12.dp)
          )
        }
      }

      // Weather Image
      AsyncImage(
        model = "${weather?.current?.condition?.weatherIcon}",
        contentDescription = stringResource(R.string.weather_image),
        modifier = Modifier.size(width = 83.dp, height = 67.dp)
      )
    }
  }
}