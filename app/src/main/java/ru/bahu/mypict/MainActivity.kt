package ru.bahu.mypict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var mainAdapter: MainAdapter
    private lateinit var mainRV: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainRV = findViewById(R.id.main_rv)
        mainAdapter = MainAdapter()
        mainRV.adapter = mainAdapter
        mainRV.layoutManager = LinearLayoutManager(this)
    }
}