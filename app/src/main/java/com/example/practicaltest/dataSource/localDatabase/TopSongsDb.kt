package com.example.practicaltest.dataSource.localDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practicaltest.dataSource.localDatabase.dao.TopSongsDao
import com.example.practicaltest.dataSource.localDatabase.entitities.SongEntity

@Database(version = 1, entities = [SongEntity::class], exportSchema = false)
abstract class TopSongsDb : RoomDatabase() {

    abstract fun topSongsDao(): TopSongsDao

    companion object {

        private const val DATABASE_NAME: String = "top_songs_db"

        @Volatile
        private var instance: TopSongsDb? = null

        fun getDatabase(context: Context): TopSongsDb =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, TopSongsDb::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

    }

}