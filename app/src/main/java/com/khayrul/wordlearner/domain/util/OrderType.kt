package com.khayrul.wordlearner.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
