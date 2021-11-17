package com.khayrul.wordlearner.presentation.words

import com.khayrul.wordlearner.domain.model.Word
import com.khayrul.wordlearner.domain.util.WordOrder

sealed class WordsEvent {
    data class Order(val wordOrder: WordOrder) : WordsEvent()
    data class ShowAlertToDeleteWord(val word: Word) : WordsEvent()
    object DeleteWord : WordsEvent()
    object DismissAlert : WordsEvent()
    object RestoreWord : WordsEvent()
    object ToggleOrderSection : WordsEvent()
}
