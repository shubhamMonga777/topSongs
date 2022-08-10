package com.example.practicaltest.dataSource.localDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practicaltest.dataSource.localDatabase.entitities.SongEntity

@Dao
interface TopSongsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(songsList: List<SongEntity>)

    @Query("SELECT * FROM songs")
    suspend fun get(): List<SongEntity>

}