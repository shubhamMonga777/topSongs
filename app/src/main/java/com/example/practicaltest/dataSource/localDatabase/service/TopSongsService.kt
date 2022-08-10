package com.example.practicaltest.dataSource.localDatabase.service

import com.example.practicaltest.dataSource.localDatabase.entitities.SongEntity

interface TopSongsService {

    suspend fun insertSongs(list: List<SongEntity>)
    suspend fun getSongs(): List<SongEntity>


}