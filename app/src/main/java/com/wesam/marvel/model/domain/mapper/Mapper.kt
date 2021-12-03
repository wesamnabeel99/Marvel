package com.wesam.marvel.model.domain.mapper

interface Mapper <I,O> {
    fun map(input: I) : O
}