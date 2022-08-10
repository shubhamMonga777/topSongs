package com.example.practicaltest.dataSource.localDatabase.cacheDataSource

import com.example.practicaltest.dataSource.localDatabase.entitities.SongEntity

interface CacheDataSource {

    suspend fun insertSongs(list: List<SongEntity>)
    suspend fun getSongs(): List<SongEntity>
}