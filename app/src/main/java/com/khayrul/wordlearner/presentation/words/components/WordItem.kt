package com.khayrul.wordlearner.presentation.words.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khayrul.wordlearner.domain.model.Word

@Composable
fun WordItem(
    modifier: Modifier = Modifier,
    word: Word
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Word: ${word.title}"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Definition: ${word.definition}")
    }
}