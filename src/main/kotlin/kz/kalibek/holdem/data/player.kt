package kz.kalibek.holdem.data

data class Player(
        val name: String = "",
        val status: PlayerStatus = PlayerStatus.Fold,
        val pot: Int = 0,
        val balance: Int = 0,
        val cards: List<Card> = emptyList(),
        val playerAction: PlayerStatus = PlayerStatus.Fold
)

enum class PlayerStatus {
    Call,
    NotMoved,
    Fold,
    Rise,
    AllIn,
    Check,
    SmallBLind,
    BigBlind
}

