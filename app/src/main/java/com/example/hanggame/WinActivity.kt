package com.example.hanggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hanggame.data.PlayerModel
import com.example.hanggame.databinding.ActivityWinBinding
import com.example.hanggame.managers.GameManager
import com.example.hanggame.services.BackgroundSoundService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class WinActivity : AppCompatActivity() {


    private lateinit var binding: ActivityWinBinding
    private val gameManager = GameManager()
    private lateinit var dbReference: DatabaseReference
    private var value : Int? = null
    private var name : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intentWin = Intent(this, NotificationsActivity::class.java)
        startService(intentWin)
        dbReference = FirebaseDatabase.getInstance().getReference("Players")

        val bundle = intent.extras
        value = bundle?.getInt("scoreInt")

        val data = bundle?.getString("score")
        binding.scoreText.text = "Final Score: ${data}"

        binding.mainMenuButtonWIn.setOnClickListener {
            addPlayerData()
            val intent = Intent(this@WinActivity, BannerActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addPlayerData() {
        val playerId = FirebaseAuth.getInstance().uid.toString()
        val name = binding.nameToSave.text.toString()

        if(name.isEmpty())
        {
            binding.nameToSave.error = "Please enter name"
        }

        val player = PlayerModel(playerId, name, value)

        dbReference.child(playerId).setValue(player).addOnCompleteListener{
        }.addOnFailureListener{ err ->
            Toast.makeText(this, "Data ${err.message}", Toast.LENGTH_SHORT).show()
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