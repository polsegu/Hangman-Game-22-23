package com.example.hanggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hanggame.databinding.ActivityMainBinding
import com.example.hanggame.services.BackgroundSoundService

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
            val intent = Intent(this@MainActivity, RankingActivity::class.java)
            startActivity(intent)
        }

        binding.optionsShow.setOnClickListener {
            //not done yet
            val intent = Intent(this@MainActivity, OptionsActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onPause() {
        BackgroundSoundService.mMediaPlayer?.pause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        BackgroundSoundService.mMediaPlayer?.start()
    }
}