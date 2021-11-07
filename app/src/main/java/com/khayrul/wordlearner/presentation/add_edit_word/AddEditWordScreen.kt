package com.khayrul.wordlearner.presentation.add_edit_word

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.khayrul.wordlearner.presentation.add_edit_word.components.HintTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditWordScreen(
    navController: NavController,
    viewModel: AddEditWordViewModel = hiltViewModel()
) {
    val title = viewModel.wordTitle.value
    val definition = viewModel.wordDefinition.value

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditWordViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditWordViewModel.UiEvent.SaveWord -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(AddEditWordEvent.SaveWord) },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save word")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            HintTextField(
                text = title.text,
                hint = title.hint,
                onValueChange = {
                                viewModel.onEvent(AddEditWordEvent.EnteredTitle(it))
                },
                textStyle = MaterialTheme.typography.h5,
                onFocusChange = {
                    viewModel.onEvent(AddEditWordEvent.ChangeTitleFocus(it))
                },
                singleLine = true,
                isHintVisible = title.isHintVisible && title.text == ""
            )
            Spacer(modifier = Modifier.height(16.dp))
            HintTextField(
                text = definition.text,
                hint = definition.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditWordEvent.EnteredDefinition(it))
                },
                textStyle = MaterialTheme.typography.body1,
                onFocusChange = {
                    viewModel.onEvent(AddEditWordEvent.ChangeDefinitionFocus(it))
                },
                isHintVisible = definition.isHintVisible && definition.text == "",

                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}