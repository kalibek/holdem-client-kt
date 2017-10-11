package kz.kalibek.holdem

import kz.kalibek.holdem.data.GameRound
import kz.kalibek.holdem.data.PlayerStatus.Rise
import javax.websocket.MessageHandler

object GameStateHandler : MessageHandler.Whole<String> {

    var counter = 0
    var gameRound = GameRound.INITIAL
    var roundMoney = 0
    var balance = 1000

    override fun onMessage(message: String?) {
        message?.let {
            val game = jsonMapToGame(message)
            val player = game.players.filter { it.name == PLAYER_NAME }.get(0)
            roundMoney = balance - player.balance;
            if (game.gameRound == GameRound.FINAL) {
                roundMoney = 0
                balance = player.balance
            }
            if (gameRound == game.gameRound) {
                counter++
            } else {
                counter = 0
                gameRound = game.gameRound
            }
            if (game.mover == PLAYER_NAME) {
                val (status, pot) = analyze(game, counter)
                when (status) {
                    Rise -> WebSocket.send(status, pot)
                    else -> WebSocket.send(status)
                }
            }

        }
    }
}