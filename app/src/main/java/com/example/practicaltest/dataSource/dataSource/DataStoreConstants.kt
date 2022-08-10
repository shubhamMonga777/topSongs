package com.example.practicaltest.dataSource.dataSource

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey

object DataStoreConstants {

    const val DataStoreName = "top_songs_datastore"

    val SONS_FETCHED = booleanPreferencesKey("songs_fetched")

}