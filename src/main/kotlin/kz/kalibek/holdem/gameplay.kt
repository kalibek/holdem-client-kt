package kz.kalibek.holdem

import kz.kalibek.holdem.data.Game
import kz.kalibek.holdem.data.PlayerStatus

fun analyze(game: Game): Pair<PlayerStatus, Int> {
    val player = game.players.filter { it.name == PLAYER_NAME }.get(0)
    val combination: Combination = getHighestCombination(game.deskCards + player.cards)

    val strategy = when (combination) {
        in Combination.PAIR..Combination.TWO_PAIRS -> RiseStrategy((player.balance * 0.05).toInt())
        in Combination.THREE_OF_A_KIND..Combination.STRAIGHT -> RiseStrategy((player.balance * 0.2).toInt())
        in Combination.FLUSH..Combination.FULL_HOUSE -> RiseStrategy((player.balance * 0.7).toInt())
        in Combination.FOUR_OF_A_KIND..Combination.ROYAL_FLUSH -> AllInStrategy()
        else -> CallStrategy()
    }

    println("======== round: ${game.gameRound} ==========")
    println(game.deskCards + player.cards)
    println(player)
    println(combination)
    println(strategy)

    return strategy.move()
}

interface GameStrategy {
    fun move(): Pair<PlayerStatus, Int>
}

class CallStrategy : GameStrategy {
    override fun move(): Pair<PlayerStatus, Int> {
        return Pair(PlayerStatus.Call, 0)
    }
}

class RiseStrategy(val amount: Int) : GameStrategy {
    override fun move(): Pair<PlayerStatus, Int> {
        return Pair(PlayerStatus.Rise, amount)
    }
}

class AllInStrategy : GameStrategy {
    override fun move(): Pair<PlayerStatus, Int> {
        return Pair(PlayerStatus.AllIn, 0)
    }
}
