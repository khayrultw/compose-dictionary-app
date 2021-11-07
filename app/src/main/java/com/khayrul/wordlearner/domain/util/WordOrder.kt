package com.khayrul.wordlearner.domain.util

sealed class WordOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): WordOrder(orderType)
    class Date(orderType: OrderType): WordOrder(orderType)

    fun copy(orderType: OrderType): WordOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
        }
    }
}
