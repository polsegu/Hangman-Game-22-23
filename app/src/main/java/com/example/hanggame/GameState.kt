package com.example.hanggame

sealed class GameState {
    class Running (val letterUsed: String, val underscoreWord: String) : GameState ()
    class Lost (val wordToGuess: String) : GameState ()
    class Won (val wordToGuess: String) : GameState()
}