package com.example.hanggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hanggame.databinding.ActivityRankingBinding

class RankingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRankingBinding

    private val itemList = ArrayList<String>()
    private lateinit var customAdapter: RankingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.recyclerView

        customAdapter = RankingAdapter(itemList)

        val layoutMang = LinearLayoutManager(applicationContext)

        recyclerView.layoutManager = layoutMang

        recyclerView.adapter = customAdapter
        completeList()

        binding.mainMenuBut.setOnClickListener {
            val intent = Intent(this@RankingActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun completeList()
    {
        itemList.add("Jordi 50000")
        itemList.add("Nahuel 40000")
        itemList.add("Pepe 15000")
        itemList.add("Pol 10000")
        itemList.add("Aka 7000")
        itemList.add("Marc 1000")
        itemList.add("Malo 5")
        itemList.add("Malisimo -50")
        customAdapter.notifyDataSetChanged()
    }
}