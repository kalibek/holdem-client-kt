package kz.kalibek.holdem.data

data class Game(
        val gameRound: GameRound = GameRound.INITIAL,
        val dealer: String = "",
        val mover: String = "",
        val event: List<String> = emptyList(),
        val players: List<Player> = emptyList(),
        val combination: String = "",
        val gameStatus: GameStatus = GameStatus.STOPPED,
        val deskCards: List<Card> = emptyList(),
        val deskPot: Int = 0
)

enum class GameRound {
    INITIAL,
    BLIND,
    THREE_CARDS,
    FOUR_CARDS,
    FIVE_CARDS,
    FINAL;
}

enum class GameStatus {
    NOT_ENOUGH_PLAYERS,
    READY,
    STARTED,
    PAUSED,
    STOPPED
}