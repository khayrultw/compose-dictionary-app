package com.khayrul.wordlearner.presentation.words

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.khayrul.wordlearner.R
import com.khayrul.wordlearner.presentation.util.Screen
import com.khayrul.wordlearner.presentation.words.components.OrderSection
import com.khayrul.wordlearner.presentation.words.components.WordItem

@Composable
fun WordsScreen(
    navController: NavController,
    wordsViewModel: WordsViewModel = hiltViewModel()
) {
    val state = wordsViewModel.state.value
    val showAlert = wordsViewModel.showDialog.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditWordScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Word")
            }
        },
        scaffoldState = scaffoldState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.your_word),
                    style = MaterialTheme.typography.h5
                )
                IconButton(
                    onClick = { wordsViewModel.onEvent(WordsEvent.ToggleOrderSection) }
                ) {
                    Icon(imageVector = Icons.Default.Sort, contentDescription = "Sort")
                }
            }
            if(state.isOderSectionVisible) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    wordOrder = state.wordOrder,
                    onOrderChange = { wordOrder ->
                        wordsViewModel.onEvent(WordsEvent.Order(wordOrder))
                    }
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.words) { word ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(1.dp)
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditWordScreen.route + "?wordId=${word.id}"
                                )
                            },
                        elevation = 4.dp
                    ) {
                        WordItem(
                            modifier = Modifier
                                .fillMaxWidth(),
                            word = word,
                            onDelete = {
                                wordsViewModel.onEvent(WordsEvent.ShowAlertToDeleteWord(word))
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        if (showAlert) {
            AlertDialog(
                onDismissRequest = {
                    wordsViewModel.onEvent(WordsEvent.DismissAlert)
                },
                title = { Text(text = stringResource(R.string.alert_title)) },
                text = { Text(text = stringResource(R.string.alert_text)) },
                confirmButton = {
                    TextButton(
                        onClick = { wordsViewModel.onEvent(WordsEvent.DeleteWord) }
                    ) {
                        Text(text = stringResource(R.string.alert_confirm_button_text))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { wordsViewModel.onEvent(WordsEvent.DismissAlert) }
                    ) {
                        Text(text = stringResource(R.string.alert_cancel_button_text))
                    }
                }
            )
        }
    }
}