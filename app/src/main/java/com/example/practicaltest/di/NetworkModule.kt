package com.example.practicaltest.di

import android.content.Context
import com.example.practicaltest.BuildConfig
import com.example.practicaltest.dataSource.appManager.AppDataManager
import com.example.practicaltest.dataSource.appManager.DataManager
import com.example.practicaltest.dataSource.dataSource.DataStoreHelper
import com.example.practicaltest.dataSource.localDatabase.cacheDataSource.CacheDataSource
import com.example.practicaltest.dataSource.network.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun providesOkhttpCache(@ApplicationContext appContext: Context): Cache {
        return Cache(appContext.cacheDir, 1024)
    }

    @Singleton
    @Provides
    fun providersLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    @Singleton
    @Provides
    fun providesOkHttpClient(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(6, TimeUnit.SECONDS)
            .writeTimeout(6, TimeUnit.SECONDS)
            .readTimeout(6, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(okHttpClient)
            .build()

    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppManager(
        mPreferencesHelper: DataStoreHelper,
        cacheDataSource: CacheDataSource,
        mApiHelper: ApiService
    ): DataManager {
        return AppDataManager(mApiHelper, cacheDataSource, mPreferencesHelper)
    }


}