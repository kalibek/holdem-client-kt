package kz.kalibek.holdem

import kz.kalibek.holdem.data.Card
import kz.kalibek.holdem.data.CardSuit
import kz.kalibek.holdem.data.CardValue
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals

object CombinationSpec : Spek({
    given("list of cards") {
        val cards = listOf<Card>(
                Card(CardSuit.SPADES, CardValue.ACE),
                Card(CardSuit.HEARTS, CardValue.ACE))
        on("analyze") {
            val rate = hasCombination(cards)
            it("should return 1 for the pair") {
                assertEquals(0, rate)
            }
        }
    }
})