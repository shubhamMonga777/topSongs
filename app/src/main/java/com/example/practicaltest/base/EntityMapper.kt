package com.example.practicaltest.base

interface EntityMapper<DomainModel, Entity> {
    fun mapToEntityModel(domainModel: DomainModel): Entity
    fun mapToDomainModel(entity: Entity): DomainModel
}