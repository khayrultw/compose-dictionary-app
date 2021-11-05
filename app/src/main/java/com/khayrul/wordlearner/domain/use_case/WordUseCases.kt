package com.khayrul.wordlearner.domain.use_case

import GetWordsUseCase

data class WordUseCases(
    val getWordsUseCase: GetWordsUseCase,
    val addWordUseCase: AddWordUseCase,
    val deleteWordUseCase: DeleteWordUseCase
)