package com.example.hanggame

import kotlin.random.Random

class GameManager {
    private var letterUsed: String = ""
    private lateinit var underscoreWord: String
    private lateinit var wordToGuess: String
    private val tries = 5
    private var currentTries = 0
    private var score = 0

    private var currentTime:Int = 0
    private var maxTime:Long = 60000

    fun startGame() : GameState {
        currentTime = 0
        score = 0
        letterUsed = ""
        currentTries = 0
        val randomIndex = Random.nextInt(0, GameConstants.words.size)
        wordToGuess = GameConstants.words[randomIndex]
        generateUnderScores(wordToGuess)
        return getGameState()
    }

    fun getCurrentTries() : Int
    {
        return currentTries
    }

    fun getMaxTime() : Long
    {
        return maxTime
    }

    fun setCurrentTime(value: Int)
    {
        currentTime = value
    }

    fun getCurrentTime():Int
    {
        return currentTime
    }

    fun getScore() : Int
    {
        return score
    }

    fun addScore(value: Int)
    {
        score += value
    }

    fun generateUnderScores(word: String)
    {
        val sb = StringBuilder()
        word.forEach { char ->
            if(char == '/'){ sb.append('/') }
            else { sb.append('_') }
        }
        underscoreWord = sb.toString();
    }

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
}