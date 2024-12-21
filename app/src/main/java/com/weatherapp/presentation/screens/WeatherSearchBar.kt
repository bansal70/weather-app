package com.weatherapp.presentation.screens

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.R
import com.weatherapp.presentation.ui.theme.Black2C
import com.weatherapp.presentation.ui.theme.RegularFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherSearchBar(searchQuery: MutableState<String>) {
  BasicTextField(
    value = searchQuery.value,
    onValueChange = { searchQuery.value = it },
    modifier = Modifier
      .height(46.dp)
      .padding(horizontal = 24.dp)
      .fillMaxWidth(),
    singleLine = true,
    textStyle = TextStyle(
      fontSize = 15.sp,
      color = Black2C,
      fontFamily = RegularFont,
      fontWeight = FontWeight.Normal,
    ),
    decorationBox = { innerTextField ->
      TextFieldDefaults.DecorationBox(
        value = searchQuery.value,
        innerTextField = innerTextField,
        enabled = true,
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        interactionSource = remember { MutableInteractionSource() },
        shape = RoundedCornerShape(15.dp),
        trailingIcon = {
          Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = stringResource(R.string.search_icon),
            tint = Color(0xFFC4C4C4)
          )
        },
        placeholder = {
          Text(
            text = stringResource(R.string.search_location),
            color = Color(0xFFC4C4C4),
            style = TextStyle(
              fontSize = 15.sp,
              fontFamily = RegularFont,
              fontWeight = FontWeight.Normal,
              color = Black2C
            ),
          )
        },
        colors = TextFieldDefaults.colors(
          unfocusedContainerColor = Color(0xFFF2F2F2),
          unfocusedIndicatorColor = Color.Transparent
        ),
        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 20.dp)
      )
    }
  )
}