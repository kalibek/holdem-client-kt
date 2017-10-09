package kz.kalibek.holdem

import kz.kalibek.holdem.data.Card
import kz.kalibek.holdem.data.CardSuit
import kz.kalibek.holdem.data.CardValue
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.*

object CombinationSpec : Spek({
    given("combination checker functions") {
        on("non pair") {
            it("should return false") {
                assertFalse(hasPair(listOf(
                        Card(CardSuit.CLUBS, CardValue.KING),
                        Card(CardSuit.CLUBS, CardValue.NINE),
                        Card(CardSuit.DIAMONDS, CardValue.TEN),
                        Card(CardSuit.SPADES, CardValue.FOUR),
                        Card(CardSuit.DIAMONDS, CardValue.ACE))))
            }
        }

        on("pair") {
            it("should return true") {
                assertTrue(hasPair(listOf(
                        Card(CardSuit.CLUBS, CardValue.KING),
                        Card(CardSuit.CLUBS, CardValue.NINE),
                        Card(CardSuit.DIAMONDS, CardValue.TEN),
                        Card(CardSuit.SPADES, CardValue.ACE),
                        Card(CardSuit.HEARTS, CardValue.ACE))))
            }
        }

        on("three of a kind") {
            it("should return true") {
                assertTrue(hasThreeOfAKind(listOf(
                        Card(CardSuit.CLUBS, CardValue.KING),
                        Card(CardSuit.CLUBS, CardValue.NINE),
                        Card(CardSuit.DIAMONDS, CardValue.JACK),
                        Card(CardSuit.CLUBS, CardValue.JACK),
                        Card(CardSuit.SPADES, CardValue.JACK)
                )))
            }
        }

        on("four of a kind") {
            it("should return true") {
                assertTrue(hasFourOfAKind(listOf(
                        Card(CardSuit.CLUBS, CardValue.JACK),
                        Card(CardSuit.SPADES, CardValue.NINE),
                        Card(CardSuit.CLUBS, CardValue.NINE),
                        Card(CardSuit.DIAMONDS, CardValue.NINE),
                        Card(CardSuit.HEARTS, CardValue.NINE)
                )))
            }
        }

        on("two pairs") {
            it("should return true when two pairs sent") {
                assertTrue(hasTwoPairs(listOf(
                        Card(CardSuit.CLUBS, CardValue.JACK),
                        Card(CardSuit.SPADES, CardValue.NINE),
                        Card(CardSuit.CLUBS, CardValue.NINE),
                        Card(CardSuit.DIAMONDS, CardValue.TEN),
                        Card(CardSuit.HEARTS, CardValue.TEN)
                )))
            }
            it("should return false when one pair sent") {
                assertFalse(hasTwoPairs(listOf(
                        Card(CardSuit.SPADES, CardValue.NINE),
                        Card(CardSuit.CLUBS, CardValue.JACK),
                        Card(CardSuit.CLUBS, CardValue.NINE),
                        Card(CardSuit.DIAMONDS, CardValue.TEN),
                        Card(CardSuit.HEARTS, CardValue.SIX)
                )))
            }

        }
        on("full house") {
            it("should return true") {
                assertTrue(hasFullHouse(listOf(
                        Card(CardSuit.SPADES, CardValue.NINE),
                        Card(CardSuit.CLUBS, CardValue.NINE),
                        Card(CardSuit.DIAMONDS, CardValue.TEN),
                        Card(CardSuit.HEARTS, CardValue.TEN),
                        Card(CardSuit.SPADES, CardValue.TEN)
                )))
            }
        }

        on("flush") {
            it("should return true when flush sent") {
                assertTrue(hasFlush(listOf(
                        Card(CardSuit.SPADES, CardValue.TEN),
                        Card(CardSuit.SPADES, CardValue.NINE),
                        Card(CardSuit.SPADES, CardValue.SEVEN),
                        Card(CardSuit.SPADES, CardValue.TEN),
                        Card(CardSuit.SPADES, CardValue.ACE)
                )))
            }
        }
        on("straight") {
            it("should return true when straight sent") {
                assertTrue(hasStraight(listOf(
                        Card(CardSuit.SPADES, CardValue.TEN),
                        Card(CardSuit.DIAMONDS, CardValue.JACK),
                        Card(CardSuit.HEARTS, CardValue.QUEEN),
                        Card(CardSuit.SPADES, CardValue.KING),
                        Card(CardSuit.SPADES, CardValue.ACE)
                )))
            }
            it("should extract straight") {
                val straight = listOf(
                        Card(CardSuit.SPADES, CardValue.TEN),
                        Card(CardSuit.DIAMONDS, CardValue.JACK),
                        Card(CardSuit.HEARTS, CardValue.QUEEN),
                        Card(CardSuit.SPADES, CardValue.KING),
                        Card(CardSuit.SPADES, CardValue.ACE)
                )
                val cards = straight + listOf(
                        Card(CardSuit.SPADES, CardValue.FOUR),
                        Card(CardSuit.SPADES, CardValue.SEVEN)
                )
                assertEquals(straight, extractStraight(cards))
            }
        }
        on("straight flush") {
            it("should return true when straight flush sent") {
                assertTrue(hasStraightFlush(listOf(
                        Card(CardSuit.SPADES, CardValue.TEN),
                        Card(CardSuit.SPADES, CardValue.JACK),
                        Card(CardSuit.SPADES, CardValue.QUEEN),
                        Card(CardSuit.SPADES, CardValue.KING),
                        Card(CardSuit.SPADES, CardValue.ACE)
                )))
            }
        }
        on("royal flush") {
            it("should return true when royal flush sent") {
                assertTrue(hasRoyalFlush(listOf(
                        Card(CardSuit.SPADES, CardValue.TEN),
                        Card(CardSuit.SPADES, CardValue.JACK),
                        Card(CardSuit.SPADES, CardValue.QUEEN),
                        Card(CardSuit.SPADES, CardValue.KING),
                        Card(CardSuit.SPADES, CardValue.ACE)
                )))
            }
        }
    }
    given("combination checker", {
        on("no combination", {
            it("should return breaker", {
                assertEquals(Combination.BREAKER, getHighestCombination(listOf(
                        Card(CardSuit.CLUBS, CardValue.KING),
                        Card(CardSuit.CLUBS, CardValue.NINE),
                        Card(CardSuit.DIAMONDS, CardValue.TEN),
                        Card(CardSuit.SPADES, CardValue.FOUR),
                        Card(CardSuit.DIAMONDS, CardValue.ACE))))
            })
        })
        on("full house", {
            it("should return full house", {
                assertEquals(Combination.FULL_HOUSE, getHighestCombination(listOf(
                        Card(CardSuit.SPADES, CardValue.NINE),
                        Card(CardSuit.CLUBS, CardValue.NINE),
                        Card(CardSuit.DIAMONDS, CardValue.TEN),
                        Card(CardSuit.HEARTS, CardValue.TEN),
                        Card(CardSuit.SPADES, CardValue.TEN)
                )))
            })
        })
    })
})