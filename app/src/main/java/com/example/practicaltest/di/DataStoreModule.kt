package com.example.practicaltest.di

import android.content.Context
import com.example.practicaltest.dataSource.dataSource.AppDataStoreImpl
import com.example.practicaltest.dataSource.dataSource.DataStoreConstants
import com.example.practicaltest.dataSource.dataSource.DataStoreHelper
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun providedDataStore(
        someString: String,
        @ApplicationContext appContext: Context,
        gson: Gson
    ): DataStoreHelper {
        return AppDataStoreImpl(appContext,someString,gson)
    }

    @Singleton
    @Provides
    fun provideString(): String {
        return DataStoreConstants.DataStoreName
    }


}