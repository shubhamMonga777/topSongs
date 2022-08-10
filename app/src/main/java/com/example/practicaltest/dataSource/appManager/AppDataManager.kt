package com.example.practicaltest.dataSource.appManager

import androidx.datastore.preferences.core.Preferences
import com.example.practicaltest.dataSource.dataSource.DataStoreHelper
import com.example.practicaltest.dataSource.localDatabase.cacheDataSource.CacheDataSource
import com.example.practicaltest.dataSource.localDatabase.entitities.SongEntity
import com.example.practicaltest.dataSource.network.ApiService
import com.example.practicaltest.dataSource.network.model.TopSongsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class AppDataManager
@Inject
constructor(
    private val mApiHelper: ApiService,
    private val cacheDataSource: CacheDataSource,
    private val mDataStoreHelper: DataStoreHelper,
) : DataManager {
    override suspend fun getTopSongs(): Response<TopSongsResponse> {
        return mApiHelper.getTopSongs()
    }

    override suspend fun <T> setKeyValue(key: Preferences.Key<T>, value: T) {
        mDataStoreHelper.setKeyValue(key, value)
    }


    override fun <T> getKeyValue(key: Preferences.Key<T>): Flow<Any?> {
        return mDataStoreHelper.getKeyValue(key)
    }


    override suspend fun <T> getGsonValue(key: Preferences.Key<String>, type: Class<T>): Flow<T?> {
        return mDataStoreHelper.getGsonValue(key, type)
    }

    override suspend fun addGsonValue(key: Preferences.Key<String>, value: String) {
        return mDataStoreHelper.addGsonValue(key, value)
    }

    override suspend fun clear() {
        mDataStoreHelper.clear()
    }

    override suspend fun insertSongs(list: List<SongEntity>) {
        cacheDataSource.insertSongs(list)
    }

    override suspend fun getSongs(): List<SongEntity> {
        return cacheDataSource.getSongs()
    }


}