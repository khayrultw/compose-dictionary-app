package com.khayrul.wordlearner.feature_words.domain.repository

import com.khayrul.wordlearner.feature_words.domain.model.Word
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    fun getWords(): Flow<List<Word>>

    suspend fun getWordById(id: Int): Word?

    suspend fun insertWord(word: Word)

    suspend fun deleteWord(word: Word)
}