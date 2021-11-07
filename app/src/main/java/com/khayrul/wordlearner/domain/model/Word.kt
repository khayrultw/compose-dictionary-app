package com.khayrul.wordlearner.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.Exception
import java.util.*

@Entity
data class Word(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val definition: String,
    val date: Long
)

class InvalidWordException(message: String) : Exception(message)
