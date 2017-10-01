package kz.kalibek.holdem

import kz.kalibek.holdem.data.PlayerStatus
import org.glassfish.tyrus.client.ClientManager
import java.net.URI
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import javax.websocket.Endpoint
import javax.websocket.EndpointConfig
import javax.websocket.Session


val wsAddress = "ws://localhost:8080/ws?user=ktBot&password=pwd"
val messageLatch = CountDownLatch(Integer.MAX_VALUE)

object WebSocket : Endpoint() {
    lateinit var session: Session

    override fun onOpen(session: Session, endpointConfig: EndpointConfig) {
        this.session = session
        this.session.addMessageHandler(KtMessageHandler)
    }

    fun send(action: PlayerStatus, pot: Int = 0) {
        val message = action.name + if (pot != 0) ", " + pot.toString() else ""
        this.session.asyncRemote.sendText(message)
    }
}

fun main(args: Array<String>): Unit {
    val clientManager = ClientManager.createClient()
    clientManager.connectToServer(WebSocket, URI.create(wsAddress))
    messageLatch.await(100, TimeUnit.SECONDS)
}
