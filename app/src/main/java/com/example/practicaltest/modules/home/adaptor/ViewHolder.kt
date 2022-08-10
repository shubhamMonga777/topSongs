package com.example.practicaltest.modules.home.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltest.R
import com.example.practicaltest.databinding.ItemSongBinding
import com.example.practicaltest.domain.model.Song

class ViewHolder(
    private val binding: ItemSongBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(song: Song, selectedSongs: (Song) -> Unit) {
        binding.song = song
        binding.parentPanel.setOnClickListener { selectedSongs(song) }
        binding.executePendingBindings()
    }

    companion object {
        fun create(
            parent: ViewGroup,
        ): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_song, parent, false)
            val binding = ItemSongBinding.bind(view)
            return ViewHolder(binding)
        }
    }
}