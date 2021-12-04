package com.wesam.marvel.model.domain.mapper

interface Mapper <DTO,ENTITY,DOMAIN> {
    fun mapDtoToDomain(input: DTO) : DOMAIN
    fun mapDtoToEntity(input: DTO):ENTITY
    fun mapEntityToDomain(input:ENTITY) : DOMAIN
}