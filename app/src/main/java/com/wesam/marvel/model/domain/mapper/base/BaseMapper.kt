package com.wesam.marvel.model.domain.mapper.base

interface BaseMapper<I, O> {
    fun map(input: I): O
}