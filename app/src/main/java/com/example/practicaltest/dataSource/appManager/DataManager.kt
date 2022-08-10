package com.example.practicaltest.dataSource.appManager

import com.example.practicaltest.dataSource.dataSource.DataStoreHelper
import com.example.practicaltest.dataSource.localDatabase.cacheDataSource.CacheDataSource
import com.example.practicaltest.dataSource.network.ApiService

interface DataManager : ApiService, DataStoreHelper, CacheDataSource