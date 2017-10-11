package kz.kalibek.holdem

import kz.kalibek.holdem.data.Game
import kz.kalibek.holdem.data.GameRound
import kz.kalibek.holdem.data.Player
import kz.kalibek.holdem.data.PlayerStatus

fun analyze(game: Game): Pair<PlayerStatus, Int> {
    val player = game.players.filter { it.name == PLAYER_NAME }.get(0)
    val combination: Combination = getHighestCombination(game.deskCards + player.cards)

    val strategy = when (game.gameRound) {
        GameRound.BLIND -> blindStrategy(player)
        GameRound.FINAL -> finalStrategy(combination, player)
        else -> gameStrategy(combination, player, game)
    }

    println("======== round: ${game.gameRound} ==========")
//    println(game.deskCards + player.cards)
//    println(player)
//    println(combination)
    println(strategy)
    println(player.balance)

    return strategy.move()
}

fun blindStrategy(player: Player): GameStrategy {
    return CallStrategy()
}

fun finalStrategy(combination: Combination, player: Player): GameStrategy {
    return when (combination) {
        in Combination.PAIR..Combination.TWO_PAIRS -> CallStrategy()
        in Combination.THREE_OF_A_KIND..Combination.STRAIGHT -> CallStrategy()
        in Combination.FLUSH..Combination.FULL_HOUSE -> rise(player, 0.8)
        in Combination.FOUR_OF_A_KIND..Combination.ROYAL_FLUSH -> AllInStrategy()
        else -> CallStrategy()
    }
}

fun gameStrategy(combination: Combination, player: Player, game: Game): GameStrategy {
    return when (combination) {
        in Combination.PAIR..Combination.TWO_PAIRS -> rise(player, 0.05)
        in Combination.THREE_OF_A_KIND..Combination.STRAIGHT -> rise(player, 0.2)
        in Combination.FLUSH..Combination.FULL_HOUSE -> rise(player, 0.7)
        in Combination.FOUR_OF_A_KIND..Combination.ROYAL_FLUSH -> AllInStrategy()
        else -> {
            val balanceCoef = player.pot / (player.pot + player.balance)
            val avgBalance = game.players.map { it.balance }.average()
            if (balanceCoef < 0.3) {
                if (player.balance > avgBalance) {
                    rise(player, 0.2)
                }
                return CallStrategy()
            }
            return FoldStrategy()
        }
    }
}

private fun rise(player: Player, coef: Double): GameStrategy {
    val balanceCoef = player.pot / (player.pot + player.balance)
    return if (balanceCoef < coef) RiseStrategy((player.balance * coef).toInt()) else FoldStrategy()
}

interface GameStrategy {
    fun move(): Pair<PlayerStatus, Int>
}

class FoldStrategy : GameStrategy {
    override fun move(): Pair<PlayerStatus, Int> {
        return Pair(PlayerStatus.Fold, 0)
    }
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
