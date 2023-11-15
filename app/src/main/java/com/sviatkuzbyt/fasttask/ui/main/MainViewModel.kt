package com.sviatkuzbyt.fasttask.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sviatkuzbyt.fasttask.data.elements.UnDoneTask
import com.sviatkuzbyt.fasttask.data.repositories.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    val list = MutableLiveData<MutableList<UnDoneTask>>()

    private lateinit var _list: MutableList<UnDoneTask>
    private val repository = MainRepository(application)

    init {
        loadList()
    }

    private fun loadList() = viewModelScope.launch(Dispatchers.IO){
        val l = repository.loadTask()
        list.postValue(l)
        Log.v("listTask", l.toString())
    }

    fun addTask(text: String) = viewModelScope.launch(Dispatchers.IO){
        repository.addTask(text)
        loadList()
    }
}