package com.example.hanggame

import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hanggame.databinding.ActivityOptionsBinding
import com.example.hanggame.services.BackgroundSoundService

class OptionsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOptionsBinding
    var volume = 100f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //AudioManager am = mContext.getSystemService(Context.AUDIO_SERVICE)

        //val am = applicationContext.getSystemService(applicationContext.audio)
        //AudioManager.adjustVol
        binding.musicSlider?.progress = 100
        binding.musicSlider?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
                val log = (1 - (Math.log((100 - seek.progress).toDouble()) / Math.log((100).toDouble()))).toFloat()
                BackgroundSoundService.mMediaPlayer?.setVolume(log, log)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            }
        })

    }
}