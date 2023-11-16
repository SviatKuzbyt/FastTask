package com.sviatkuzbyt.fasttask.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sviatkuzbyt.fasttask.R
import com.sviatkuzbyt.fasttask.ui.elements.CreateTaskEditText
import com.sviatkuzbyt.fasttask.ui.recycler.TaskAdapter

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val toolbar: Toolbar by lazy { findViewById(R.id.mainToolbar) }
    private val recyclerTasks: RecyclerView by lazy { findViewById(R.id.recyclerTasks) }
    private lateinit var recyclerAdapter: TaskAdapter
    private val createTask: CreateTaskEditText by lazy { findViewById(R.id.createTask) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerTasks.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = TaskAdapter(viewModel)
        recyclerTasks.adapter = recyclerAdapter

        viewModel.list.observe(this){
            when(viewModel.getListState()){
                ListChanges.LoadAll -> recyclerAdapter.addAll(it)

                ListChanges.AddTask ->{
                    recyclerAdapter.addElement()
                    recyclerTasks.scrollToPosition(0)
                }
                else -> {}
            }
            viewModel.setDefaultListState()
        }

        createTask.setOnEditorActionListener { textView, _, _ ->
            if(textView.text.isNotEmpty()){
                viewModel.addTask(textView.text.toString())
                textView.text = ""
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}