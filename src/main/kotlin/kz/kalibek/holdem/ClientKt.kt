package kz.kalibek.holdem

import org.glassfish.tyrus.client.ClientManager
import java.net.URI
import java.util.concurrent.CountDownLatch

const val PLAYER_NAME = "lameBot"
val wsAddress = "ws://localhost:8080/ws?user=$PLAYER_NAME&password=pwd"
val messageLatch = CountDownLatch(Integer.MAX_VALUE)

class ClientKt{
    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit {
            val clientManager = ClientManager.createClient()
            clientManager.connectToServer(WebSocket, URI.create(wsAddress))
            messageLatch.await()
        }
    }
}