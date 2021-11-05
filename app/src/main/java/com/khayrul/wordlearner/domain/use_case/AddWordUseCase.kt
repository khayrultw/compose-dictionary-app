package com.khayrul.wordlearner.domain.use_case

import com.khayrul.wordlearner.domain.model.Word
import com.khayrul.wordlearner.domain.repository.WordRepository
import javax.inject.Inject

class AddWordUseCase @Inject constructor(
    private val wordRepository: WordRepository
) {
    suspend operator fun invoke(word: Word) {
        wordRepository.insertWord(word)
    }
}