package com.example.hanggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hanggame.databinding.ActivityLoseBinding
import com.example.hanggame.services.BackgroundSoundService

class LoseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentLose = Intent(this, NotificationsActivity::class.java)
        startService(intentLose)

        binding = ActivityLoseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainMenuButton.setOnClickListener {
            val intent = Intent(this@LoseActivity, BannerActivity::class.java)
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