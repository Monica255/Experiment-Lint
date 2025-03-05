package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var userAdapter: AdapterUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = MyData.getData()

            val layout = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layout//test

        userAdapter =
            AdapterUser {
            }
        Log.d("adapterrrr", data.toString())
        userAdapter.list = data.toMutableList()
        binding.rvUser.adapter = userAdapter
    }
}
