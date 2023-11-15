package com.sviatkuzbyt.fasttask.data.room

import android.content.Context
import androidx.room.Room

object DatabaseManager {
    private var taskDataBase: TaskDataBase? = null

    fun getTaskDao(context: Context): TaskDao {
        return getDatabase(context).taskDao()
    }

    private fun getDatabase(context: Context): TaskDataBase {
        if (taskDataBase == null) {
            synchronized(DatabaseManager::class.java) {
                if (taskDataBase == null) {
                    taskDataBase = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDataBase::class.java, "task_database"
                    ).build()
                }
            }
        }
        return taskDataBase!!
    }
}