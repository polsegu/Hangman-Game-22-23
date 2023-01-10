package com.example.hanggame.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.hanggame.R


class BackgroundSoundService : Service() {

    override fun onBind(arg0: Intent): IBinder? {

        return null
    }

    override fun onCreate() {
        super.onCreate()
        playSound()
        //val afd = applicationContext.assets.openFd(R.raw.back) as AssetFileDescriptor
        //player.setVolume(100f, 100f)
    }

    override fun onStartCommand(startIntent: Intent?, flags: Int, startId: Int): Int {
        mMediaPlayer = MediaPlayer.create(this, R.raw.background_sound_hangman)
        mMediaPlayer!!.isLooping = true
        mMediaPlayer!!.start()
        mMediaPlayer!!.setVolume(100f, 100f);
        return START_STICKY;
    }
    private fun playSound() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.background_sound_hangman)
            mMediaPlayer!!.isLooping = true
            mMediaPlayer!!.start()
        } else mMediaPlayer!!.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMediaPlayer?.stop()
    }
    fun onUnBind(arg0: Intent): IBinder? {
        // TO DO Auto-generated method
        return null
    }

    companion object {
        var mMediaPlayer: MediaPlayer? = MediaPlayer()
        private val TAG: String? = null
    }
}