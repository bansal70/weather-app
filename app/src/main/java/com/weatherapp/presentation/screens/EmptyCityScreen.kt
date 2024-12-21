package com.weatherapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.R
import com.weatherapp.presentation.ui.theme.Black2C
import com.weatherapp.presentation.ui.theme.SemiboldFont

@Composable
fun EmptyCityScreen() {
  Column(
    modifier = Modifier
      .fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = stringResource(R.string.title_no_city),
      style = TextStyle(
        fontSize = 30.sp,
        fontFamily = SemiboldFont,
        fontWeight = FontWeight.SemiBold,
        color = Black2C
      ),
      textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = stringResource(R.string.text_search_city),
      style = TextStyle(
        fontSize = 15.sp,
        fontFamily = SemiboldFont,
        fontWeight = FontWeight.SemiBold,
        color = Black2C
      )
    )
  }
}