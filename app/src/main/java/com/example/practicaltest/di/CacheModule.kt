package com.example.practicaltest.di

import android.content.Context
import com.example.practicaltest.base.EntityMapper
import com.example.practicaltest.dataSource.localDatabase.TopSongsDb
import com.example.practicaltest.dataSource.localDatabase.cacheDataSource.CacheDataSource
import com.example.practicaltest.dataSource.localDatabase.cacheDataSource.CacheDataSourceImpl
import com.example.practicaltest.dataSource.localDatabase.dao.TopSongsDao
import com.example.practicaltest.dataSource.localDatabase.dao.TopSongsDaoImpl
import com.example.practicaltest.dataSource.localDatabase.entitities.SongEntity
import com.example.practicaltest.dataSource.localDatabase.service.TopSongsService
import com.example.practicaltest.dataSource.mappers.SongEntityMapper
import com.example.practicaltest.dataSource.mappers.SongNetworkMapper
import com.example.practicaltest.dataSource.network.model.Entry
import com.example.practicaltest.domain.model.Song
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideSongEntityMapper(): EntityMapper<Song, SongEntity> {
        return SongEntityMapper()
    }

    @Singleton
    @Provides
    fun provideSongNetworkMapper(): EntityMapper<Entry, Song> {
        return SongNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideLocalDb(
        @ApplicationContext context: Context,
    ) = TopSongsDb.getDatabase(context)

    @Singleton
    @Provides
    fun provideTopSongsDao(topSongsDb: TopSongsDb): TopSongsDao {
        return topSongsDb.topSongsDao()
    }

    @Singleton
    @Provides
    fun provideDaoService(
        topSongsDao: TopSongsDao
    ): TopSongsService {
        return TopSongsDaoImpl(topSongsDao)
    }

    @Singleton
    @Provides
    fun provideCacheDataSource(
        topSongsService: TopSongsService
    ): CacheDataSource {
        return CacheDataSourceImpl(topSongsService)
    }


}