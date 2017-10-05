package kz.kalibek.holdem.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Card(
        val cardSuit: CardSuit = CardSuit.SPADES,
        val cardValue: CardValue = CardValue.TWO
)

enum class CardValue(val order: Int) {
    @JsonProperty("2") TWO(1),
    @JsonProperty("3") THREE(2),
    @JsonProperty("4") FOUR(3),
    @JsonProperty("5") FIVE(4),
    @JsonProperty("6") SIX(5),
    @JsonProperty("7") SEVEN(6),
    @JsonProperty("8") EIGHT(7),
    @JsonProperty("9") NINE(8),
    @JsonProperty("10") TEN(9),
    @JsonProperty("J") JACK(10),
    @JsonProperty("Q") QUEEN(11),
    @JsonProperty("K") KING(12),
    @JsonProperty("A") ACE(13)

}

enum class CardSuit {
    @JsonProperty("♠") SPADES,
    @JsonProperty("♥") HEARTS,
    @JsonProperty("♦") DIAMONDS,
    @JsonProperty("♣") CLUBS
}
