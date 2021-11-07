package com.khayrul.wordlearner.di

import GetWordsUseCase
import android.app.Application
import androidx.room.Room
import com.khayrul.wordlearner.data.data_source.WordDatabase
import com.khayrul.wordlearner.data.repository.WordRepositoryImpl
import com.khayrul.wordlearner.domain.repository.WordRepository
import com.khayrul.wordlearner.domain.use_case.AddWordUseCase
import com.khayrul.wordlearner.domain.use_case.DeleteWordUseCase
import com.khayrul.wordlearner.domain.use_case.GetWordUseCase
import com.khayrul.wordlearner.domain.use_case.WordUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWordDatabase(app: Application): WordDatabase {
        return Room.databaseBuilder(
            app,
            WordDatabase::class.java,
            WordDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideWordRepository(db: WordDatabase): WordRepository {
        return WordRepositoryImpl(db.wordDao)
    }

    @Provides
    @Singleton
    fun provideWordUseCases(repository: WordRepository): WordUseCases {
        return WordUseCases(
            getWords = GetWordsUseCase(repository),
            addWord = AddWordUseCase(repository),
            deleteWord = DeleteWordUseCase(repository),
            getWord = GetWordUseCase(repository)
        )
    }
}