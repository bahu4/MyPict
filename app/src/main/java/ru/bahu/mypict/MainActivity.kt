package ru.bahu.mypict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.bahu.mypict.data.Data

class MainActivity : AppCompatActivity() {
    private var mainAdapter: MainAdapter? = null
    private lateinit var mainRV: RecyclerView
    private val onItemClickListener: MainAdapter.OnItemClickListener =
        object : MainAdapter.OnItemClickListener {
            override fun onItemClick(data: Data) {
                Toast.makeText(this@MainActivity, data.title, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainRV = findViewById(R.id.main_rv)
        mainAdapter = MainAdapter(onItemClickListener)
        mainRV.adapter = mainAdapter
        mainRV.layoutManager = LinearLayoutManager(applicationContext)
    }
}