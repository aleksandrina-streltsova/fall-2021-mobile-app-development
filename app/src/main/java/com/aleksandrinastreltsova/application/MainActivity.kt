 package com.aleksandrinastreltsova.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

 class MainActivity : AppCompatActivity(R.layout.activity_main) {
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         val recyclerView = findViewById<RecyclerView>(R.id.usersRecyclerView)
         recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
         val adapter = UserAdapter()
         recyclerView.adapter = adapter
         adapter.userList = loadUsers()
         adapter.notifyDataSetChanged()
     }

     private fun loadUsers(): List<User> {
         return listOf(
                User("","User1","Math"),
                User("","User2","Physics")
         )
     }
}