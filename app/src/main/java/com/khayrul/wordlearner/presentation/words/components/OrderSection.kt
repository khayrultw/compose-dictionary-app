package com.khayrul.wordlearner.presentation.words.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khayrul.wordlearner.domain.util.OrderType
import com.khayrul.wordlearner.domain.util.WordOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    wordOrder: WordOrder = WordOrder.Title(OrderType.Ascending),
    onOrderChange: (WordOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                selected = wordOrder is WordOrder.Title,
                onClick = { onOrderChange(WordOrder.Title(wordOrder.orderType)) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = wordOrder is WordOrder.Date,
                onClick = { onOrderChange(WordOrder.Date(wordOrder.orderType)) }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = wordOrder.orderType is OrderType.Ascending,
                onClick = {
                    onOrderChange(wordOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = wordOrder.orderType is OrderType.Descending,
                onClick = {
                    onOrderChange(wordOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}