package kz.kalibek.holdem

import kz.kalibek.holdem.data.PlayerStatus.Rise
import javax.websocket.MessageHandler

object KtMessageHandler : MessageHandler.Whole<String> {
    override fun onMessage(message: String?) {
        message?.let {
            val game = jsonMapToGame(message);
            val (status, pot) = analyze(game)
            when (status) {
                Rise -> WebSocket.send(status, pot)
                else -> WebSocket.send(status)
            }
        }
    }

}