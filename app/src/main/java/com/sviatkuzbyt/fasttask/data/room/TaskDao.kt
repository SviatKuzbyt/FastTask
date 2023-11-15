package com.sviatkuzbyt.fasttask.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sviatkuzbyt.fasttask.data.elements.*

@Dao
interface TaskDao {
    @Query("SELECT id, text, position FROM Task WHERE isDone=0 ORDER BY position")
    fun getUnDoneTasks(): MutableList<UnDoneTask>

    @Query("SELECT id, text FROM Task WHERE isDone=1 ORDER BY position")
    fun getDoneTasks(): MutableList<DoneTask>

    @Insert
    fun addTask(task: Task)

    @Query("UPDATE Task SET text=:text WHERE id=:id")
    fun updateTask(id: Int, text: String)

    @Query("UPDATE Task SET isDone=:isDone WHERE id=:id")
    fun updateDone(id: Int, isDone: Boolean)

    @Query("DELETE FROM Task WHERE id=:id")
    fun delete(id: Int)

}