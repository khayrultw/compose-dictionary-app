package com.khayrul.wordlearner.presentation.words

import com.khayrul.wordlearner.domain.model.Word
import com.khayrul.wordlearner.domain.util.OrderType
import com.khayrul.wordlearner.domain.util.WordOrder

data class WordsState(
    val words: List<Word> = emptyList(),
    val wordOrder: WordOrder = WordOrder.Title(OrderType.Ascending),
    val isOderSectionVisible: Boolean = false
)
