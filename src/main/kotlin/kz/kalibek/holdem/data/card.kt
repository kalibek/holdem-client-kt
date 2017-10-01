package kz.kalibek.holdem.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Card(
        val cardSuit: CardSuit = CardSuit.SPADES,
        val cardValue: CardValue = CardValue.TWO
)

enum class CardValue {
    @JsonProperty("2") TWO,
    @JsonProperty("3") THREE,
    @JsonProperty("4") FOUR,
    @JsonProperty("5") FIVE,
    @JsonProperty("6") SIX,
    @JsonProperty("7") SEVEN,
    @JsonProperty("8") EIGHT,
    @JsonProperty("9") NINE,
    @JsonProperty("10") TEN,
    @JsonProperty("J") JACK,
    @JsonProperty("Q") QUEEN,
    @JsonProperty("K") KING,
    @JsonProperty("A") ACE

}

enum class CardSuit {
    @JsonProperty("♠") SPADES,
    @JsonProperty("♥") HEARTS,
    @JsonProperty("♦") DIAMONDS,
    @JsonProperty("♣") CLUBS
}
