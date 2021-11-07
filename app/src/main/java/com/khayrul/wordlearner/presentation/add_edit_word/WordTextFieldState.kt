package com.khayrul.wordlearner.presentation.add_edit_word

data class WordTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
