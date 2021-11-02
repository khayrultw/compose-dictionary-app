package com.khayrul.wordlearner.feature_words.data.data_source

import androidx.room.*
import com.khayrul.wordlearner.feature_words.domain.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM word")
    fun getWords(): Flow<List<Word>>

    @Query("SELECT * FROM word WHERE id = :id")
    suspend fun getWordById(id: Int): Word?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)
}