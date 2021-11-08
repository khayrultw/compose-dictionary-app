package com.khayrul.wordlearner.presentation.words

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khayrul.wordlearner.domain.model.Word
import com.khayrul.wordlearner.domain.use_case.WordUseCases
import com.khayrul.wordlearner.domain.util.OrderType
import com.khayrul.wordlearner.domain.util.WordOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordsViewModel @Inject constructor(
    private val wordUseCase: WordUseCases
) : ViewModel() {

    private val _state = mutableStateOf<WordsState>(WordsState())
    val state = _state

    private var recentlyDeletedWord: Word? = null
    private var getWordsJob: Job? = null

    init {
        getWords(WordOrder.Title(OrderType.Ascending))
    }

    fun onEvent(event: WordsEvent) {
        when(event) {
            is WordsEvent.Order -> {
                if(state.value.wordOrder::class == event.wordOrder::class
                    && state.value.wordOrder.orderType == event.wordOrder.orderType
                ) {
                    return
                }
                _state.value = state.value.copy(
                    wordOrder = event.wordOrder
                )

                getWords(event.wordOrder)
            }

            is WordsEvent.DeleteWord -> {
                viewModelScope.launch {
                    wordUseCase.deleteWord(event.word)
                    recentlyDeletedWord = event.word
                }
            }

            is WordsEvent.RestoreWord -> {
                viewModelScope.launch {
                    wordUseCase.addWord(recentlyDeletedWord ?: return@launch)
                    recentlyDeletedWord = null

                }
            }

            is WordsEvent.ToggleOrderSection -> {
                _state.value = _state.value.copy(
                    isOderSectionVisible = !state.value.isOderSectionVisible
                )
            }
        }
    }

    private fun getWords(wordOrder: WordOrder = WordOrder.Title(OrderType.Ascending)) {
        getWordsJob?.cancel()

        getWordsJob = wordUseCase.getWords(wordOrder)
            .onEach { words ->
                _state.value = state.value.copy(
                    words = words,
                    wordOrder = wordOrder
                )
            }
            .launchIn(viewModelScope)
    }
}