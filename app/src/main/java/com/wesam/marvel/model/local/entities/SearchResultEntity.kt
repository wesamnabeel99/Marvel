package com.wesam.marvel.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SEARCH_RESULTS_TABLE")
data class SearchResultEntity(
    val searchQuery: String,
    @PrimaryKey
    val resultId: Long,
)