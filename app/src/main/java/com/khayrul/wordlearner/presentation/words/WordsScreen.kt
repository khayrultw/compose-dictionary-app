package com.khayrul.wordlearner.presentation.words

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.khayrul.wordlearner.presentation.util.Screen
import com.khayrul.wordlearner.presentation.words.components.OrderSection
import com.khayrul.wordlearner.presentation.words.components.WordItem

@Composable
fun WordsScreen(
    navController: NavController,
    wordsViewModel: WordsViewModel = hiltViewModel()
) {
    val state = wordsViewModel.state.value
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
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Your words",
                    style = MaterialTheme.typography.h5
                )
                IconButton(
                    onClick = { wordsViewModel.onEvent(WordsEvent.ToggleOrderSection) }
                ) {
                    Icon(imageVector = Icons.Default.Sort, contentDescription = "Sort")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            if(state.isOderSectionVisible) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    wordOrder = state.wordOrder,
                    onOrderChange = { wordOrder ->
                        wordsViewModel.onEvent(WordsEvent.Order(wordOrder))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.words) { word ->
                    WordItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditWordScreen.route + "?wordId=${word.id}"
                                )
                            },
                        word = word
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}