package com.khayrul.wordlearner.presentation.add_edit_word

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khayrul.wordlearner.domain.model.InvalidWordException
import com.khayrul.wordlearner.domain.model.Word
import com.khayrul.wordlearner.domain.use_case.WordUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditWordViewModel @Inject constructor(
    private val wordUseCases: WordUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _wordTitle = mutableStateOf(WordTextFieldState(
        hint = "Enter Title"
    ))
    val wordTitle: State<WordTextFieldState> = _wordTitle

    private val _wordDefinition = mutableStateOf(WordTextFieldState(
        hint = "Enter the definition"
    ))
    val wordDefinition: State<WordTextFieldState> = _wordDefinition

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow

    private var currentWordId: Int? = null

    init {
        savedStateHandle.get<Int>("wordId")?.let { wordId ->
            if(wordId != -1) {
                viewModelScope.launch {
                    wordUseCases.getWord(wordId)?.also { word ->
                        currentWordId = wordId
                        _wordTitle.value = wordTitle.value.copy(
                            text = word.title,
                            isHintVisible = false
                        )
                        _wordDefinition.value = wordDefinition.value.copy(
                            text = word.definition,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditWordEvent) {
        when(event) {
            is AddEditWordEvent.EnteredTitle -> {
                _wordTitle.value  = wordTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditWordEvent.EnteredDefinition -> {
                _wordDefinition.value = wordDefinition.value.copy(
                    text = event.value
                )
            }
            is AddEditWordEvent.ChangeTitleFocus -> {
                _wordTitle.value  = wordTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused
                )
            }
            is AddEditWordEvent.ChangeDefinitionFocus -> {
                _wordDefinition.value  = wordDefinition.value.copy(
                    isHintVisible = !event.focusState.isFocused
                )
            }
            is AddEditWordEvent.SaveWord -> {
                viewModelScope.launch {
                    try {
                        wordUseCases.addWord(
                            Word(
                                title = wordTitle.value.text,
                                definition = wordDefinition.value.text,
                                date = System.currentTimeMillis(),
                                id = currentWordId,
                                status = "Learning"
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveWord)
                    } catch (e: InvalidWordException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save word"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveWord: UiEvent()
    }
}