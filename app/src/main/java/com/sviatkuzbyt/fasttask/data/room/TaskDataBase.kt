package com.sviatkuzbyt.fasttask.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}