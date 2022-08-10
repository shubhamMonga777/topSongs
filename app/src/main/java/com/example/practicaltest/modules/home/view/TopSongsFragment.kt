package com.example.practicaltest.modules.home.view

import android.util.Log
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.practicaltest.R
import com.example.practicaltest.base.BaseFragment
import com.example.practicaltest.databinding.FragmentTopSongsBinding
import com.example.practicaltest.modules.home.adaptor.SongsAdaptor
import com.example.practicaltest.modules.home.viewModel.TopSongsViewModel
import com.example.practicaltest.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@DelicateCoroutinesApi
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class TopSongsFragment : BaseFragment<FragmentTopSongsBinding, TopSongsViewModel>() {

    private val viewModel: TopSongsViewModel by viewModels()
    private lateinit var binding: FragmentTopSongsBinding
    private lateinit var songsAdaptor: SongsAdaptor

    override val layoutResourceId: Int
        get() = R.layout.fragment_top_songs

    override fun getVM(): TopSongsViewModel = viewModel

    override fun initView() {
        binding = getViewDataBinding()
        songsAdaptor = SongsAdaptor { song ->
            lifecycleScope.launch {
                delay(200)
                val bundle = bundleOf(Constants.SONG_MODEL to song)
                findNavController().navigate(
                    R.id.action_topSongsFragment_to_songDetailFragment,
                    bundle
                )
            }
        }
        binding.songAdaptor = songsAdaptor
        observers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvSongs.adapter = null
    }

    private fun observers() {

        viewModel.progressLiveEvent.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }

        viewModel.topSongList.observe(viewLifecycleOwner) { result ->
            if (songsAdaptor.currentList.isEmpty()) {
                songsAdaptor.submitList(result)
            }

        }
    }

    override fun initData() {
        viewModel.fetchSongs()
    }

}