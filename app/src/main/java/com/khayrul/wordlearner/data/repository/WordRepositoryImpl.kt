package com.khayrul.wordlearner.data.repository

import com.khayrul.wordlearner.data.data_source.WordDao
import com.khayrul.wordlearner.domain.model.Word
import com.khayrul.wordlearner.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow

class WordRepositoryImpl(
    private val dao: WordDao
): WordRepository {
    override fun getWords(): Flow<List<Word>> {
        return dao.getWords()
    }

    override suspend fun getWordById(id: Int): Word? {
        return dao.getWordById(id)
    }

    override suspend fun insertWord(word: Word) {
        dao.insertWord(word)
    }

    override suspend fun deleteWord(word: Word) {
        dao.deleteWord(word)
    }
}