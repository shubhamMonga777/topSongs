package com.example.practicaltest.dataSource.dataSource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AppDataStoreImpl
@Inject
constructor(
    private val context: Context,
    dataStoreName: String,
    private val gson: Gson
) : DataStoreHelper {


    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = dataStoreName)

    override suspend fun <T> setKeyValue(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }


    override fun <T> getKeyValue(key: Preferences.Key<T>): Flow<Any?> {

        return context.dataStore.data
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map { preference ->
                preference[key]
            }
    }


    override suspend fun <T> getGsonValue(key: Preferences.Key<String>, type: Class<T>): Flow<T?> {

        return context.dataStore.data
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map { preference ->
                fetchGson(gson = preference[key] ?: "", type = type, key = key.name)
            }

    }

    override suspend fun addGsonValue(key: Preferences.Key<String>, value: String) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }


    private fun <T> fetchGson(gson: String, type: Class<T>, key: String): T? {
        return if (gson.isEmpty()) {
            null
        } else {
            try {
                this.gson.fromJson(gson, type)
            } catch (e: Exception) {
                throw IllegalArgumentException(
                    "Object storaged with key "
                            + key + " is instanceof other class"
                )
            }

        }
    }
}