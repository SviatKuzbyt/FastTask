package com.sviatkuzbyt.fasttask.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sviatkuzbyt.fasttask.R
import com.sviatkuzbyt.fasttask.ui.recycler.TaskAdapter

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val toolbar: Toolbar by lazy { findViewById(R.id.mainToolbar) }
    private val recyclerTasks: RecyclerView by lazy { findViewById(R.id.recyclerTasks) }
    private lateinit var recyclerAdapter: TaskAdapter
    private val createTask: EditText by lazy { findViewById(R.id.createTask) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerTasks.layoutManager = LinearLayoutManager(this)
        viewModel.list.observe(this){
            recyclerAdapter = TaskAdapter(it)
            recyclerTasks.adapter = recyclerAdapter
        }

        createTask.setOnEditorActionListener { textView, i, keyEvent ->
            viewModel.addTask(textView.text.toString())
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}