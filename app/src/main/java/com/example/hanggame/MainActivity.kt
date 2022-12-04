package com.example.hanggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hanggame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playActivity.setOnClickListener {
            val intent = Intent(this@MainActivity, PlayActivity::class.java)
            startActivity(intent)
        }

        binding.rankingShow.setOnClickListener {
            //not done yet
        }

        binding.optionsShow.setOnClickListener {
            //not done yet
        }
    }
}