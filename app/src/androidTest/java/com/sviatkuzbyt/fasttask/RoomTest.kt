package com.sviatkuzbyt.fasttask

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.sviatkuzbyt.fasttask.data.room.DatabaseManager
import com.sviatkuzbyt.fasttask.data.room.Task
import com.sviatkuzbyt.fasttask.data.room.TaskDao
import org.junit.Test
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class RoomTest {

    lateinit var taskDao: TaskDao
    val taskOne = Task(0, "Make dinner", 1)
    val taskTwo = Task(0, "Make bed", 1)
    val change = "clear room"

    @Before
    fun setup(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        taskDao = DatabaseManager.getTaskDao(context)
    }

    @Test
    fun addTasks() = runTest {
        taskDao.addTask(taskOne)
        taskDao.addTask(taskTwo)
    }

    @Test
    fun checkInsert() = runTest {
        val taskList = taskDao.getDoneTasks()
        Assert.assertTrue(taskList.all{it.text == taskOne.text})
    }

    @Test
    fun setDoneTask() = runTest {
        taskDao.updateDone(0, true)
        val taskList = taskDao.getDoneTasks()
        Assert.assertTrue(taskList.all{it.text == taskOne.text})
    }

    @Test
    fun changeTask() = runTest {
        taskDao.updateTask(1, change)
        val taskList = taskDao.getUnDoneTasks()
        Assert.assertTrue(taskList.all{it.text == change})
    }

    @Test
    fun deleteTask() = runTest {
        taskDao.delete(0)
        val taskList = taskDao.getUnDoneTasks()
        Assert.assertTrue(taskList.all{it.text == taskOne.text})
    }
}