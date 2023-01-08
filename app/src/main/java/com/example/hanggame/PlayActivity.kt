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

        //Elimina las letras una vez usadas, ya que no se podrán volver a utilizar
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

        //Mira el tiempo si no se acaba lo actualiza, si se acaba ejecuta Lose
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

    private fun updateUI(gameState: GameState) {
        when (gameState) {
            is GameState.Lost -> showGameLost()
            is GameState.Won -> showGameWon()
            is GameState.Running -> {
                scoreShow.text = "Score: ${gameManager.score}"
                wordTextView.text = gameState.underscoreWord
                letterUsed.text = "Letters used: ${gameState.letterUsed}"
                showTries.text = "Tries: ${gameManager.currentTries} / 5"
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
        startActivity(intent)
        finish()
    }

    private fun startGame()
    {
        val gameState = gameManager.startGame()
        lettersLayouts.visibility = View.VISIBLE
        lettersLayouts.children.forEach { letterView->
            letterView.visibility = View.VISIBLE
        }
        updateUI(gameState)
    }

}