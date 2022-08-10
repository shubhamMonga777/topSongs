package com.example.practicaltest.dataSource.localDatabase.cacheDataSource

import com.example.practicaltest.dataSource.localDatabase.entitities.SongEntity
import com.example.practicaltest.dataSource.localDatabase.service.TopSongsService
import javax.inject.Inject

class CacheDataSourceImpl
@Inject
constructor(
    private val topSongsService: TopSongsService,
) : CacheDataSource {

    override suspend fun insertSongs(list: List<SongEntity>) {
        return topSongsService.insertSongs(list)
    }

    override suspend fun getSongs(): List<SongEntity> {
        return topSongsService.getSongs()
    }


}