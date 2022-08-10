package com.example.practicaltest.dataSource.mappers

import com.example.practicaltest.base.EntityMapper
import com.example.practicaltest.dataSource.localDatabase.entitities.SongEntity
import com.example.practicaltest.domain.model.Song
import javax.inject.Inject

class SongEntityMapper
@Inject
constructor() : EntityMapper<Song, SongEntity> {

    override fun mapToEntityModel(domainModel: Song): SongEntity {
        return SongEntity(
            title = domainModel.title,
            link = domainModel.link,
            image = domainModel.image,
            artistName = domainModel.artistName,
            category = domainModel.category,
            price = domainModel.price
        )
    }

    override fun mapToDomainModel(entity: SongEntity): Song {
        return Song(
            title = entity.title ?: "",
            link = entity.link ?: "",
            id = entity.id,
            image = entity.image ?: "",
            artistName = entity.artistName ?: "",
            category = entity.category ?: "",
            price = entity.price ?: ""
        )
    }

    fun mapFromEntityList(entities: List<SongEntity>): List<Song> {
        return entities.map { mapToDomainModel(it) }
    }

    fun mapToList(products: List<Song>): List<SongEntity> {
        return products.map { mapToEntityModel(it) }
    }

}