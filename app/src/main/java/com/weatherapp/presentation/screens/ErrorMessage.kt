package com.weatherapp.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weatherapp.R
import com.weatherapp.domain.model.ErrorType

@Preview(showBackground = true)
@Composable
fun ErrorMessage(errorType: ErrorType = ErrorType.NETWORK_ERROR) {
  val errorMessage = when (errorType) {
    ErrorType.EMPTY -> stringResource(R.string.empty_results)
    ErrorType.UNKNOWN_ERROR -> stringResource(R.string.error_unknown)
    ErrorType.NETWORK_ERROR -> stringResource(R.string.error_internet_connection)
  }
  Text(
    text = errorMessage,
    color = Color.Red,
    modifier = Modifier.padding(16.dp)
  )
}