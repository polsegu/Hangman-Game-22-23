package com.example.hanggame

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.hanggame.databinding.ActivityLoginBinding
import android.view.View
import android.widget.Toast
import android.util.Patterns
import com.example.hanggame.services.BackgroundSoundService


import com.google.firebase.auth.FederatedAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var timer : CountDownTimer
    private var mMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //Enable music service
        val intent = Intent(this, BackgroundSoundService::class.java)
        startService(intent)

        //Show splash screen
        timer = object : CountDownTimer(2500, 1000) {
            override fun onTick(p0: Long) {
                setTheme(R.style.SplashTheme)
            }
            override fun onFinish() {}
        }.start()

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        //Auto log in (already loged in)


        //Go to Register
        binding.txtSignUp.setOnClickListener {
            val intent2 = Intent(this, NotificationsActivity::class.java)
            startService(intent2)
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        //Sign in verification (first time)
        binding.loginButton.setOnClickListener {
            val username = binding.userInputWrapper.text.toString()
            val password = binding.userInputWrapper.text.toString()

            firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }

        //Anonymous LogIn
        binding.loginAnonymusButton.setOnClickListener {
            firebaseAuth.signInAnonymously().addOnCompleteListener(this) {task ->
                if(task.isSuccessful)
                {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //Check if email is correct or not
        binding.userInputWrapper.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus)
            {
                val username = binding.userInputWrapper.text.toString()
                if(!Patterns.EMAIL_ADDRESS.matcher(username).matches())
                    binding.userInputWrapper.error = "Invalid email"
                else
                    binding.userInputWrapper.error = null
            }
        }

    }
}