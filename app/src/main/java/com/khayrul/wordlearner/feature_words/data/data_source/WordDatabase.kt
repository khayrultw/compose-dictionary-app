package com.khayrul.wordlearner.feature_words.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.khayrul.wordlearner.feature_words.domain.model.Word

@Database(
    entities = [Word::class],
    version = 1
)
abstract class WordDatabase : RoomDatabase() {
    abstract val wordDao: WordDao
}