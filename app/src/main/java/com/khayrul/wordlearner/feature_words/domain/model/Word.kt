package com.khayrul.wordlearner.feature_words.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey
    val id: Int? = null,
    val word: String,
    val definition: String
)
