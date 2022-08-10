package com.example.practicaltest.dataSource.network

import com.example.practicaltest.dataSource.network.model.TopSongsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(NetworkParams.TOP_SONGS)
    suspend fun getTopSongs(
    ): Response<TopSongsResponse>

}