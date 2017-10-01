package kz.kalibek.holdem

import com.fasterxml.jackson.databind.ObjectMapper
import kz.kalibek.holdem.data.Game

fun jsonMapToGame(json: String): Game {
    try {
        val mapper = ObjectMapper()
        return mapper.readValue(json, Game::class.java)
    } catch (e: Exception) {
        println(e)
    }
    return Game()
}


