package com.sviatkuzbyt.fasttask.data.repositories

import android.content.Context
import com.sviatkuzbyt.fasttask.data.elements.UnDoneTask
import com.sviatkuzbyt.fasttask.data.room.DatabaseManager
import com.sviatkuzbyt.fasttask.data.room.Task

class MainRepository(context: Context) {
    private val dao = DatabaseManager.getTaskDao(context)

    fun loadTask() = dao.getUnDoneTasks()


    fun addTask(text: String){
        val task = Task(0, text, 1)
        dao.addTask(task)
    }
}