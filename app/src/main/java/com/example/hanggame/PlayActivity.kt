package com.example.hanggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class PlayActivity : AppCompatActivity() {

    private lateinit var wordTextView: TextView
    private lateinit var letterUsed: TextView
    private lateinit var lettersLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        wordTextView = findViewById(R.id.wordTextView)
        letterUsed = findViewById(R.id.letterUsed)
        lettersLayout = findViewById(R.id.lettersLayout)

    }

}