package com.example.practicaltest.modules.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.practicaltest.base.BaseViewModel
import com.example.practicaltest.dataSource.appManager.AppDataManager
import com.example.practicaltest.dataSource.dataSource.DataStoreConstants
import com.example.practicaltest.dataSource.mappers.SongEntityMapper
import com.example.practicaltest.dataSource.mappers.SongNetworkMapper
import com.example.practicaltest.domain.model.Song
import com.example.practicaltest.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
@ExperimentalCoroutinesApi
class TopSongsViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val appDataManager: AppDataManager,
    private val networkMapper: SongNetworkMapper,
    private val songEntityMapper: SongEntityMapper

) : BaseViewModel() {

    private val _topSongsList = SingleLiveEvent<List<Song>>()
    val topSongList: LiveData<List<Song>> get() = _topSongsList


    private fun fetchTopSongsFromServer() {
        launchAsync({ appDataManager.getTopSongs() }, {
            if (it.songsList?.isNotEmpty() == true) {
                val mappedValue = networkMapper.mapFromEntityList(it.songsList!!)
                _topSongsList.value = mappedValue
                updateValueInDataStore()
                storeSongsInDb(mappedValue)
            }
        })

    }


    fun fetchSongs() {
        viewModelScope.launch(Dispatchers.IO) {
            appDataManager.getKeyValue(DataStoreConstants.SONS_FETCHED).collect {
                val fetchFromDb = it as Boolean?
                if (fetchFromDb == true)
                    fetchSongsFromDb()
                else fetchTopSongsFromServer()
            }

        }
    }

    private suspend fun fetchSongsFromDb() {
        val dbSongs = appDataManager.getSongs()
        if (dbSongs.isNotEmpty()) {
            val mappedSongs = songEntityMapper.mapFromEntityList(dbSongs)
            launchOnUI {
                _topSongsList.value = mappedSongs
            }
        }

    }

    private fun storeSongsInDb(mappedValue: List<Song>) {
        viewModelScope.launch(Dispatchers.IO) {
            appDataManager.insertSongs(songEntityMapper.mapToList(mappedValue))
        }
    }

    private fun updateValueInDataStore() {
        viewModelScope.launch {
            appDataManager.setKeyValue(DataStoreConstants.SONS_FETCHED, true)
        }
    }

}