package kz.kalibek.holdem

import org.glassfish.tyrus.client.ClientManager
import java.net.URI
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import javax.websocket.Endpoint
import javax.websocket.EndpointConfig
import javax.websocket.MessageHandler
import javax.websocket.Session


val wsAddress = "ws://localhost:8080/ws?user=ktBot&password=pwd"
val messageLatch = CountDownLatch(1)

object WebSocket : Endpoint() {

    override fun onOpen(session: Session, endpointConfig: EndpointConfig) {
        println("socket is open")
        session.addMessageHandler(KtMessageHandler)
        println(session)
    }


}

object KtMessageHandler : MessageHandler.Whole<String> {
    override fun onMessage(message: String?) {
        println(message)
    }
}

fun main(args: Array<String>): Unit {

    val clientManager = ClientManager.createClient()
    clientManager.connectToServer(WebSocket, URI.create(wsAddress))
    messageLatch.await(100, TimeUnit.SECONDS)

}
