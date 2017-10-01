package kz.kalibek.holdem.data

data class Player (
        val name: String,
        val status: PlayerStatus
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
