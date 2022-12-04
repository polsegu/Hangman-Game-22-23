package com.example.hanggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)


        wordTextView = binding.wordTextView
        letterUsed = binding.letterUsed
        lettersLayouts = binding.lettersLayout
        showTries = binding.triesText

        val gameState = gameManager.startGame()
        updateUI(gameState)

        lettersLayouts.children.forEach { letterView ->
            if(letterView is TextView)
            {
                letterView.setOnClickListener{
                    val gameState = gameManager.play((letterView).text[0])
                    updateUI(gameState)
                    letterView.visibility = View.GONE
                }
            }
        }
    }

    private fun updateUI(gameState: GameState) {
        when (gameState) {
            is GameState.Lost -> showGameLost()
            is GameState.Won -> showGameWon()
            is GameState.Running -> {
                wordTextView.text = gameState.underscoreWord
                letterUsed.text = "Letters used: ${gameState.letterUsed}"
                showTries.text = "Tries: ${gameManager.getCurrentTries()} / 5"
            }
        }
    }
    private fun showGameLost()
    {
        val intent = Intent(this@PlayActivity, Lose::class.java)
        startActivity(intent)
    }

    private fun showGameWon()
    {
        val intent = Intent(this@PlayActivity, Win::class.java)
        startActivity(intent)
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