package com.example.practicaltest.modules.songDetail.view

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.practicaltest.R
import com.example.practicaltest.base.BaseFragment
import com.example.practicaltest.databinding.FragmentSongDetailBinding
import com.example.practicaltest.domain.model.Song
import com.example.practicaltest.modules.songDetail.viewModel.SongDetailViewModel
import com.example.practicaltest.util.Constants
import com.example.practicaltest.util.isInternetAvailable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.io.IOException


@DelicateCoroutinesApi
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class SongDetailFragment : BaseFragment<FragmentSongDetailBinding, SongDetailViewModel>() {

    private val viewModel: SongDetailViewModel by viewModels()
    private lateinit var binding: FragmentSongDetailBinding
    private var song: Song? = null
    private var isPLAYING = false
    private val mediaPlayer: MediaPlayer by lazy { MediaPlayer() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            song = it.getParcelable(Constants.SONG_MODEL)
        }

    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_song_detail

    override fun getVM(): SongDetailViewModel = viewModel

    override fun initView() {
        binding = getViewDataBinding()
        clicks()
        binding.song = song
    }

    private fun clicks() {
        binding.btnPlaySong.setOnClickListener {
            if (requireContext().isInternetAvailable())
                songAction() else
                Toast.makeText(
                    requireContext(),
                    getString(R.string.no_internet),
                    Toast.LENGTH_SHORT
                ).show()
        }
    }

    private fun songAction() {
        if (!isPLAYING) {
            binding.btnPlaySong.isEnabled = false
            lifecycleScope.launch(Dispatchers.IO) {
                startPlaying()
            }

        } else {
            isPLAYING = false
            binding.btnPlaySong.text = getString(R.string.play_song)
            stopPlaying()
        }
    }

    private fun startPlaying() {
        isPLAYING = true
        try {
            mediaPlayer.setDataSource(song?.link)
            mediaPlayer.prepare()
            mediaPlayer.start()
            lifecycleScope.launch(Dispatchers.Main) {
                binding.btnPlaySong.text = getString(R.string.stopSong)
                binding.btnPlaySong.isEnabled = true
            }
        } catch (e: IOException) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
        }

    }

    override fun initData() {

    }

    private fun stopPlaying() {
        mediaPlayer.reset()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.release()
    }
}