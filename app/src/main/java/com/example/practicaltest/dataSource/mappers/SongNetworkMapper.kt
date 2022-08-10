package com.example.practicaltest.dataSource.mappers

import com.example.practicaltest.base.EntityMapper
import com.example.practicaltest.dataSource.network.model.Entry
import com.example.practicaltest.domain.model.Song
import com.example.practicaltest.util.Constants
import javax.inject.Inject

class SongNetworkMapper
@Inject
constructor() : EntityMapper<Entry, Song> {

    override fun mapToEntityModel(entity: Entry): Song {
        return Song(
            title = entity.title,
            link = entity.link?.find { it.type == Constants.AUDIO_FILE_TYPE }?.linkValue ?: "",
            image = entity.image?.find { it.height == Constants.IMAGE_HEIGHT }?.value ?: "",
            artistName = entity.artist?.value ?: "",
            category = entity.category?.term ?: "",
            price = entity.price?.value ?: ""
        )
    }

    fun mapFromEntityList(entities: List<Entry>): List<Song> {
        return entities.map { mapToEntityModel(it) }
    }

    override fun mapToDomainModel(domainModel: Song): Entry {
        return Entry()
    }
}