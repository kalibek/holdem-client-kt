package kz.kalibek.holdem

import kz.kalibek.holdem.data.Card
import kz.kalibek.holdem.data.CardValue

enum class Combination {
    BREAKER,
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
    val sameValueMap = cards.groupBy(Card::cardValue).filter { it.value.size > repeats }
    return sameValueMap.count() > count
}

fun hasSameCardSuit(cards: List<Card>, count: Int = 5): Boolean {
    val sameSuitMap = cards.groupBy(Card::cardSuit).filter { it.value.size == count }
    return sameSuitMap.isNotEmpty()
}

fun hasCardValue(cards: List<Card>, cardValue: CardValue) = cards.map(Card::cardValue).contains(cardValue)

fun hasCardsInOrder(cards: List<Card>): Boolean {
    var counter = 0
    enumValues<CardValue>().forEach {
        counter = if (hasCardValue(cards, it)) counter + 1 else 0
        if (counter == 5) return true
    }
    return false
}

fun extractStraight(cards: List<Card>): List<Card> {
    var straight = emptyList<Card>()
    cards.sortedBy { it.cardValue.ordinal }.forEachIndexed { index, card ->
        var result = true
        var i = 0
        while (i < 5 && result) {
            if (card.cardValue.ordinal + i <= CardValue.ACE.ordinal) {
                val nextVal = CardValue.values()[card.cardValue.ordinal + i]
                result = result && hasCardValue(cards, nextVal)
            } else {
                result = false
            }
            i++
        }
        if (result) straight = cards.sortedBy { it.cardValue.ordinal }.slice(IntRange(index, index + 4))
    }
    return straight
}

fun hasPair(cards: List<Card>) = hasSameCardValue(cards)
fun hasThreeOfAKind(cards: List<Card>) = hasSameCardValue(cards, 2)
fun hasFourOfAKind(cards: List<Card>) = hasSameCardValue(cards, 3)
fun hasTwoPairs(cards: List<Card>) = hasSameCardValue(cards, count = 1)
fun hasFullHouse(cards: List<Card>) = hasTwoPairs(cards) && hasThreeOfAKind(cards)
fun hasFlush(cards: List<Card>) = hasSameCardSuit(cards)
fun hasStraight(cards: List<Card>) = hasCardsInOrder(cards)
fun hasStraightFlush(cards: List<Card>) = hasFlush(extractStraight(cards));
fun hasRoyalFlush(cards: List<Card>) = hasStraightFlush(cards) && hasCardValue(extractStraight(cards), CardValue.ACE)

fun getHighestCombination(cards: List<Card>): Combination {
    val combinationChecker = mapOf<Combination, (cards: List<Card>)-> Boolean>(
            Pair(Combination.ROYAL_FLUSH, ::hasRoyalFlush),
            Pair(Combination.STRAIGHT_FLUSH, ::hasStraightFlush),
            Pair(Combination.FOUR_OF_A_KIND, ::hasFourOfAKind),
            Pair(Combination.FULL_HOUSE, ::hasFullHouse),
            Pair(Combination.FLUSH, ::hasFlush),
            Pair(Combination.STRAIGHT, ::hasStraight),
            Pair(Combination.THREE_OF_A_KIND, ::hasThreeOfAKind),
            Pair(Combination.TWO_PAIRS, ::hasTwoPairs),
            Pair(Combination.PAIR, ::hasPair)
    )

    combinationChecker.forEach {
        if(it.value(cards)) return it.key
    }
    return Combination.BREAKER
}
