 package com.aleksandrinastreltsova.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.withCreated
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

 class MainActivity : AppCompatActivity(R.layout.activity_main) {
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         val recyclerView = findViewById<RecyclerView>(R.id.usersRecyclerView)
         val adapter = UserAdapter()
         recyclerView.adapter = adapter
         lifecycleScope.launch {
             lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                 adapter.userList = loadUsers()
                 adapter.notifyDataSetChanged()
             }
         }
     }

     private suspend fun loadUsers(): List<User> {
         return withContext(Dispatchers.IO) {
             Thread.sleep(3000)
             provideApi().getUsers().data
         }
     }

     private fun provideApi(): Api {
         return Retrofit.Builder()
             .client(provideOkHttpClient())
             .baseUrl("https://reqres.in/api/")
             .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
             .build()
             .create(Api::class.java)
     }

     private fun provideOkHttpClient(): OkHttpClient {
         return OkHttpClient.Builder().build()
     }

     private fun provideMoshi(): Moshi {
         return Moshi.Builder().build()
     }

}