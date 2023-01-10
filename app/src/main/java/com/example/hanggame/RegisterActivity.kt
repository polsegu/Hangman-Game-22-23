package com.example.hanggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hanggame.databinding.ActivityRegisterBinding
import com.example.hanggame.services.BackgroundSoundService
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        //Registration verification
        binding.buttonRegister.setOnClickListener {
            val email = binding.inputUserName.text.toString()
            val password = binding.inputPassword.text.toString()

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful)
                    {
                        Toast.makeText(this, "Registrared succesfully", Toast.LENGTH_SHORT).show()
                        val intentRegister = Intent(this, NotificationsActivity::class.java)
                        startService(intentRegister)
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this, "Invalid registration", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.alreadyLogIn.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
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