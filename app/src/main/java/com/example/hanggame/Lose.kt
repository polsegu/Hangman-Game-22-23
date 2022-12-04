package com.example.hanggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.hanggame.databinding.ActivityLoseBinding
import com.example.hanggame.databinding.ActivityMainBinding

class Lose : AppCompatActivity() {

    private lateinit var binding: ActivityLoseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainMenuButton.setOnClickListener {
            val intent = Intent(this@Lose, MainActivity::class.java)
            startActivity(intent)
        }
    }
}