package com.sviatkuzbyt.fasttask.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sviatkuzbyt.fasttask.data.elements.UnDoneTask
import com.sviatkuzbyt.fasttask.data.repositories.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class ListChanges{
    data object LoadAll: ListChanges()
    data object AddTask: ListChanges()
    data object Empty: ListChanges()
    data object Delete: ListChanges()

}
class MainViewModel(application: Application): AndroidViewModel(application) {
    val list = MutableLiveData<MutableList<UnDoneTask>>()

    private lateinit var _list: MutableList<UnDoneTask>
    private val repository = MainRepository(application)
    private var currentListState: ListChanges = ListChanges.Empty

    init { loadList() }

    private fun loadList() = viewModelScope.launch(Dispatchers.IO){
        _list = repository.loadTask()
        currentListState = ListChanges.LoadAll
        list.postValue(_list)
    }

    fun addTask(text: String) = viewModelScope.launch(Dispatchers.IO){
        _list.add(0, repository.addTaskAndReturnListTask(text))
        currentListState = ListChanges.AddTask
        list.postValue(_list)
    }

    fun getListState() = currentListState
    fun setDefaultListState(){
        currentListState = ListChanges.LoadAll
    }

    fun removeItem(id: Long) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteTask(id)
        _list.removeAll { it.id == id }
        currentListState = ListChanges.Delete
        list.postValue(_list)
    }

}