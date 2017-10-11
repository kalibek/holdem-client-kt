package kz.kalibek.holdem

import kz.kalibek.holdem.data.PlayerStatus
import javax.websocket.CloseReason
import javax.websocket.Endpoint
import javax.websocket.EndpointConfig
import javax.websocket.Session
import kotlin.system.exitProcess

object WebSocket : Endpoint() {
    lateinit var session: Session

    override fun onOpen(session: Session, endpointConfig: EndpointConfig) {
        this.session = session
        this.session.addMessageHandler(GameStateHandler)
        println("Web Socket connection open")
    }

    override fun onClose(session: Session?, closeReason: CloseReason?) {
        println("Web Socket connection closed")
        exitProcess(1000)
    }

    fun send(action: PlayerStatus, pot: Int = 0) {
        val message = action.name + if (pot != 0) ", " + pot.toString() else ""
        this.session.asyncRemote.sendText(message)
    }
}

