package com.khayrul.wordlearner.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val definition: String,
    val date: String
)
