package com.example.practicaltest.modules.songDetail.viewModel

import androidx.lifecycle.SavedStateHandle
import com.example.practicaltest.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
@ExperimentalCoroutinesApi
class SongDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle

) : BaseViewModel() {
}