package kz.kalibek.holdem.data

data class Card(
        val cardSuit: CardSuit,
        val cardValue: CardValue
)

enum class CardValue(val shortName: String, val fullName: String) {
    TWO("2", "2"),
    THREE("3", "3"),
    FOUR("4", "4"),
    FIVE("5", "5"),
    SIX("6", "6"),
    SEVEN("7", "7"),
    EIGHT("8", "8"),
    NINE("9", "9"),
    TEN("10", "10"),
    JACK("J", "Jack"),
    QUEEN("Q", "Queen"),
    KING("K", "King"),
    ACE("A", "Ace");

}

enum class CardSuit(val suit: String) {
    SPADES("♠"),
    HEARTS("♥"),
    DIAMONDS("♦"),
    CLUBS("♣");
}
