package com.example.practicaltest.dataSource.dataSource

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreHelper {

    suspend  fun<T> setKeyValue(key: Preferences.Key<T>, value: T)

    fun <T> getKeyValue(key: Preferences.Key<T>): Flow<Any?>

    suspend fun <T> getGsonValue(key: Preferences.Key<String>, type: Class<T>): Flow<T?>

    suspend fun addGsonValue(key: Preferences.Key<String>, value: String)

    suspend fun clear()

}