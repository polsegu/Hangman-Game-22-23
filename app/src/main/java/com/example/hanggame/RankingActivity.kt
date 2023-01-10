package com.example.hanggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hanggame.data.PlayerModel
import com.example.hanggame.databinding.ActivityRankingBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RankingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRankingBinding

    private val itemList = ArrayList<String>()
    private lateinit var customAdapter: RankingAdapter
    private lateinit var playersList : ArrayList<PlayerModel>
    private lateinit var dbRef : FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.recyclerView

        customAdapter = RankingAdapter(itemList)

        val layoutMang = LinearLayoutManager(applicationContext)

        recyclerView.layoutManager = layoutMang

        recyclerView.adapter = customAdapter

        playersList = arrayListOf<PlayerModel>()

        getPlayerData()

        binding.mainMenuBut.setOnClickListener {
            val intent = Intent(this@RankingActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getPlayerData() {
        FirebaseDatabase.getInstance().getReference("Players").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                playersList.clear()
                if(snapshot.exists())
                {
                    for(playerSnap in snapshot.children)
                    {
                        val playerData = playerSnap.getValue(PlayerModel::class.java)
                        playersList.add(playerData!!)
                    }
                    val sortedNumbers = playersList.sortedBy { it.playerScore }

                    sortedNumbers.asReversed().forEach {
                        itemList.add("" + it.playerName + "    " + it.playerScore)
                    }
                    customAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}