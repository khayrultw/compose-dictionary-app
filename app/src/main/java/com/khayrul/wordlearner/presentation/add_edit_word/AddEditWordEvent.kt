package com.khayrul.wordlearner.presentation.add_edit_word

import androidx.compose.ui.focus.FocusState

sealed class AddEditWordEvent {
    data class EnteredTitle(val value: String): AddEditWordEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditWordEvent()
    data class EnteredDefinition(val value: String): AddEditWordEvent()
    data class ChangeDefinitionFocus(val focusState: FocusState): AddEditWordEvent()
    object SaveWord: AddEditWordEvent()
}