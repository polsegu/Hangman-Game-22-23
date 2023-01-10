package com.example.hanggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.example.hanggame.databinding.ActivityPlayBinding
import com.example.hanggame.managers.GameManager
import com.example.hanggame.util.PrefUtils


class PlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayBinding

    private val gameManager = GameManager()
    private lateinit var wordTextView: TextView
    private lateinit var letterUsed: TextView
    private lateinit var lettersLayouts: ConstraintLayout
    private lateinit var showTries: TextView
    private lateinit var timerShow: TextView
    private lateinit var scoreShow: TextView
    private lateinit var timer : CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timerShow = binding.timer
        scoreShow = binding.score
        wordTextView = binding.wordTextView
        letterUsed = binding.letterUsed
        lettersLayouts = binding.lettersLayout
        showTries = binding.triesText

        val gameState = gameManager.startGame()
        updateUI(gameState)

        binding.playButton.background.alpha = 120

        binding.playButton.setOnClickListener {
            binding.pauseButton.visibility = View.VISIBLE
            binding.playButton.visibility = View.INVISIBLE
            gameManager.stateTimer = GameManager.TimerState.Running
            gameManager.maxTime = PrefUtils.getPreviusTimerSecs(this)
            gameManager.stateTimer = PrefUtils.getPreviousState(this)
            startGameTimer()
        }
        binding.pauseButton.setOnClickListener {
            timer.cancel()
            binding.playButton.visibility = View.VISIBLE
            binding.pauseButton.visibility = View.INVISIBLE
            PrefUtils.setPreviousTimerSecs((gameManager.currentTime * 1000).toLong(), this)
            PrefUtils.setPreviousState(gameManager.stateTimer, this)
            gameManager.stateTimer = GameManager.TimerState.Paused
        }

        if(gameManager.stateTimer == GameManager.TimerState.Running)
        { //Mira el tiempo si no se acaba lo actualiza, si se acaba ejecuta Lose
            lettersLayouts.children.forEach { letterView ->
                if(letterView is TextView)
                {
                    letterView.setOnClickListener{
                        val gameState = gameManager.play((letterView).text[0])
                        updateUI(gameState)
                        letterView.visibility = View.INVISIBLE
                    }
                }
            }
            startGameTimer()
        }
    }

    fun startGameTimer()
    {        //Mira el tiempo si no se acaba lo actualiza, si se acaba ejecuta Lose
        timer = object : CountDownTimer(gameManager.maxTime, 1000) {
            override fun onTick(p0: Long) {
                timerShow.text = "Time left: ${(p0 / 1000).toInt()}"
                gameManager.currentTime = (p0 / 1000).toInt()
            }
            override fun onFinish() {
                val intent = Intent(this@PlayActivity, LoseActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }
    override fun onResume() {
        super.onResume()
        if(gameManager.stateTimer == GameManager.TimerState.Paused)
        {
            gameManager.maxTime = PrefUtils.getPreviusTimerSecs(this)
            startGameTimer()
        }
        gameManager.stateTimer = GameManager.TimerState.Running
    }
    override fun onPause() {
        super.onPause()
        timer.cancel()
        gameManager.stateTimer = GameManager.TimerState.Paused
        PrefUtils.setPreviousTimerSecs((gameManager.currentTime*1000).toLong(), this)
        PrefUtils.setPreviousState(gameManager.stateTimer, this)
    }

    //refresh all the UI
    private fun updateUI(gameState: GameState) {
        when (gameState) {
            is GameState.Lost -> showGameLost()
            is GameState.Won -> showGameWon()
            is GameState.Running -> {

                scoreShow.text = "Score: ${gameManager.score}"
                wordTextView.text = gameState.underscoreWord
                letterUsed.text = "Letters used: ${gameState.letterUsed}"
                showTries.text = "Tries: ${gameManager.currentTries} / ${gameManager.tries}"
            }
        }
    }

    private fun showGameLost()
    {
        timer.cancel()
        val intent = Intent(this@PlayActivity, LoseActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showGameWon()
    {
        timer.cancel()
        gameManager.addScore(gameManager.score * gameManager.currentTime)
        val intent = Intent(this@PlayActivity, WinActivity::class.java)
        intent.putExtra("score", (gameManager.score).toString())
        intent.putExtra("scoreInt", gameManager.score)
        startActivity(intent)
        finish()
    }

}