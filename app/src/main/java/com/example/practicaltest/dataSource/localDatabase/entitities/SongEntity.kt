package com.example.practicaltest.dataSource.localDatabase.entitities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "link") val link: String? = null,
    @ColumnInfo(name = "image") val image: String? = null,
    @ColumnInfo(name = "artistName") val artistName: String? = null,
    @ColumnInfo(name = "price") val price: String? = null,
    @ColumnInfo(name = "category") val category: String? = null
)
