package com.wesam.marvel.model.domain.mapper

interface Mapper <I,O,O2> {
    fun mapToDomain(input: I) : O
    fun mapToEntitiy(input: I):O2
}