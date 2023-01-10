package com.example.hanggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hanggame.databinding.ActivityWinBinding
import com.example.hanggame.managers.GameManager

class WinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWinBinding
    private val gameManager = GameManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val data = bundle?.getString("score")
        binding.scoreText.text = "Final Score: ${data}"

        binding.mainMenuButtonWIn.setOnClickListener {
            val intent = Intent(this@WinActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}