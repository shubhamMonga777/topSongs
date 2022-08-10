package com.example.practicaltest.modules.home.adaptor

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltest.domain.model.Song

class SongsAdaptor(
    private val selectSong: (Song) -> Unit
) : ListAdapter<Song, RecyclerView.ViewHolder>(SONG_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            when (holder) {
                is ViewHolder -> holder.bind(item, selectSong)
            }
        }
    }

    companion object {

        private val SONG_COMPARATOR = object : DiffUtil.ItemCallback<Song>() {
            override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean =
                oldItem.id == newItem.id
        }

    }

}