package kz.kalibek.holdem

import kz.kalibek.holdem.data.Card

enum class Combination {
    PAIR,
    TWO_PAIRS,
    THREE_OF_A_KIND,
    STRAIGHT,
    FLUSH,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    STRAIGHT_FLUSH,
    ROYAL_FLUSH
}

fun hasSameCardValue(cards: List<Card>, repeats: Int = 1, count: Int = 0): Boolean {
    val sameValueMap = cards.groupBy { it.cardValue }.filter { it.value.size > repeats }
    return sameValueMap.count() > count
}
fun hasSameCardSuit(cards: List<Card>, count: Int = 5): Boolean {
    val sameSuitMap = cards.groupBy { it.cardSuit }.filter { it.value.size == count }
    return sameSuitMap.isNotEmpty()
}
fun hasPair(cards: List<Card>) = hasSameCardValue(cards)
fun hasThreeOfAKind(cards: List<Card>) = hasSameCardValue(cards, 2)
fun hasFourOfAKind(cards: List<Card>) = hasSameCardValue(cards, 3)
fun hasTwoPairs(cards: List<Card>) = hasSameCardValue(cards, count = 1)
fun hasFullHouse(cards: List<Card>) = hasTwoPairs(cards) && hasThreeOfAKind(cards)

fun hasFlush(cards: List<Card>) = hasSameCardSuit(cards)
