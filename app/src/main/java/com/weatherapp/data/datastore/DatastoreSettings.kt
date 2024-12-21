package com.weatherapp.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore by preferencesDataStore(name = "weather_preferences")

class DatastoreSettings @Inject constructor(@ApplicationContext private val context: Context) {

  object PreferenceKeys {
    val CITY_KEY = stringPreferencesKey("selected_city")
  }

  suspend fun updateCity(city: String?) {
    context.dataStore.edit {
      it[PreferenceKeys.CITY_KEY] = city.orEmpty()
    }
  }

  val getSelectedCity: Flow<String?>
    get() = context.dataStore.data.map {
      it[PreferenceKeys.CITY_KEY]
    }
}
