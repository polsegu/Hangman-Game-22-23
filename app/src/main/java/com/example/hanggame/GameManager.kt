package com.example.hanggame

import kotlin.random.Random

class GameManager {
    private var letterUsed: String = ""
    lateinit var underscoreWord: String
    lateinit var wordToGuess: String
    val tries = 5
    var currentTries = 0
    var score = 0
    var currentTime:Int = 0
    var maxTime:Long = 60000
    var stateTimer: TimerState = TimerState.Stopped

    enum class TimerState {
        Running, Paused, Stopped
    }

    //Genera el juego al empezar y resetea todo
    fun startGame() : GameState {
        stateTimer = TimerState.Running
        currentTime = 0
        score = 0
        letterUsed = ""
        currentTries = 0
        val randomIndex = Random.nextInt(0, GameConstants.words.size)
        wordToGuess = GameConstants.words[randomIndex]
        generateUnderScores(wordToGuess)
        return getGameState()
    }

    fun addScore(value: Int)
    {
        score += value
    }

    //Genera las _ segun las letras de la palabra a adivinar
    fun generateUnderScores(word: String)
    {
        val sb = StringBuilder()
        word.forEach { char ->
            if(char == '/'){ sb.append('/') }
            else { sb.append('_') }
        }
        underscoreWord = sb.toString();
    }

    //Comprueba y devuelve el estado del juego dependiendo de si la adivina o pasa de los intentos permitidos
    fun getGameState(): GameState {
        if(underscoreWord.equals(wordToGuess, true))
        {
            return GameState.Won(wordToGuess)
        }
        if(currentTries == tries)
        {
            return GameState.Lost(wordToGuess)
        }

        return GameState.Running(letterUsed, underscoreWord)
    }

    fun play(letter: Char) : GameState
    {
        if(letterUsed.contains(letter))
        {
            return GameState.Running(letterUsed, underscoreWord)
        }
        letterUsed += "$letter"
        val indexs = mutableListOf<Int>()

        wordToGuess.forEachIndexed { index, char ->
            if(char.equals(letter, true)) {
                indexs.add(index)
            }
        }
        var finalUnderscore = "" + underscoreWord
        indexs.forEach { index ->
            val sb = StringBuilder(finalUnderscore).also { it.setCharAt(index, letter) }
            finalUnderscore = sb.toString()
        }
        if(indexs.isEmpty())
        {
            addScore(-50)
            currentTries++
        }
        else
        {
            addScore(200)
        }
        underscoreWord = finalUnderscore

        return getGameState()
    }
    //Check if a string is null and return it's value
    fun checkNullString(word : String?): String
    {
        if(word == null)
        {
            return "null"
        }
        else
        {
            return word
        }
    }
}