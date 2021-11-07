package com.khayrul.wordlearner.domain.use_case

import com.khayrul.wordlearner.domain.model.InvalidWordException
import com.khayrul.wordlearner.domain.model.Word
import com.khayrul.wordlearner.domain.repository.WordRepository
import javax.inject.Inject

class AddWordUseCase @Inject constructor(
    private val wordRepository: WordRepository
) {
    @Throws(InvalidWordException::class)
    suspend operator fun invoke(word: Word) {
        if(word.title.isBlank()){
            throw InvalidWordException("Title can not be empty")
        }
        if(word.definition.isBlank()){
            throw InvalidWordException("Definition can not be empty")
        }
        wordRepository.insertWord(word)
    }
}