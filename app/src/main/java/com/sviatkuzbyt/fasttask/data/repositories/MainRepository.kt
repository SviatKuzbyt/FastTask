package com.sviatkuzbyt.fasttask.data.repositories

import android.content.Context
import com.sviatkuzbyt.fasttask.data.elements.UnDoneTask
import com.sviatkuzbyt.fasttask.data.room.DatabaseManager
import com.sviatkuzbyt.fasttask.data.room.Task

class MainRepository(context: Context) {
    private val dao = DatabaseManager.getTaskDao(context)

    fun loadTask() = dao.getUnDoneTasks()

    fun addTaskAndReturnListTask(text: String, position: Int = 1): UnDoneTask{
        val id = dao.addTask(Task(0, text, position))
        return UnDoneTask(id, text, position)
    }

    fun deleteTask(id: Long){
        dao.delete(id)
    }

    fun makeDoneTask(id: Long){
        dao.updateDone(id, true)
    }

    fun updateTask(id: Long, text: String){
        dao.updateTask(id, text)
    }

}