package com.khayrul.wordlearner.presentation.util

sealed class Screen(val route: String) {
    object WordsScreen: Screen("words_screen")
    object AddEditWordScreen: Screen("add_edit_word_screen")
}
