package com.example.practicaltest.dataSource.localDatabase.dao

import com.example.practicaltest.dataSource.localDatabase.entitities.SongEntity
import com.example.practicaltest.dataSource.localDatabase.service.TopSongsService
import javax.inject.Inject

class TopSongsDaoImpl
@Inject
constructor(
    private val topSongsDao: TopSongsDao,
) : TopSongsService {
    override suspend fun insertSongs(list: List<SongEntity>) {
        topSongsDao.insertList(list)
    }

    override suspend fun getSongs(): List<SongEntity> {
        return topSongsDao.get()
    }

}