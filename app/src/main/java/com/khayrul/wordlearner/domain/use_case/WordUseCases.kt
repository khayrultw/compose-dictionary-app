package com.khayrul.wordlearner.domain.use_case

import GetWordsUseCase

data class WordUseCases(
    val getWords: GetWordsUseCase,
    val addWord: AddWordUseCase,
    val deleteWord: DeleteWordUseCase,
    val getWord: GetWordUseCase
)