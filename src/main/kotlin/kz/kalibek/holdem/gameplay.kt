package kz.kalibek.holdem

import kz.kalibek.holdem.data.Game
import kz.kalibek.holdem.data.PlayerStatus

fun analyze(game: Game): Pair<PlayerStatus, Int> {
    return Pair(PlayerStatus.Call, 0)
}