package com.sviatkuzbyt.fasttask.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sviatkuzbyt.fasttask.data.elements.UnDoneTask
import com.sviatkuzbyt.fasttask.data.repositories.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class ListChanges{
    data object LoadAll: ListChanges()
    data object AddTask: ListChanges()
    data object Empty: ListChanges()
    data object Delete: ListChanges()
    data object Edit: ListChanges()

}
class MainViewModel(application: Application): AndroidViewModel(application) {
    val list = MutableLiveData<MutableList<UnDoneTask>>()
    private var changePosition = 0

    private lateinit var _list: MutableList<UnDoneTask>
    private val repository = MainRepository(application)
    private var currentListState: ListChanges = ListChanges.Empty

    init { loadList() }


    private fun loadList() = viewModelScope.launch(Dispatchers.IO){
        _list = repository.loadTask()

        withContext(Dispatchers.Main){
            currentListState = ListChanges.LoadAll
            list.postValue(_list)
        }
    }

    fun addTask(text: String) = viewModelScope.launch(Dispatchers.IO){
        val task = repository.addTaskAndReturnListTask(text)
        withContext(Dispatchers.Main){
            _list.add(0, task)
            currentListState = ListChanges.AddTask
            list.postValue(_list)
        }

    }

    fun getListState() = currentListState
    fun setDefaultListState(){
        currentListState = ListChanges.LoadAll
    }

    fun deleteTask(id: Long, position: Int) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteTask(id)

        withContext(Dispatchers.Main){
            removeItemFromList(id, position)
        }
    }

    fun makeDoneTask(id: Long, position: Int) = viewModelScope.launch(Dispatchers.IO){
        repository.makeDoneTask(id)
        withContext(Dispatchers.Main){
            removeItemFromList(id, position)
        }
    }

    private fun removeItemFromList(id: Long, position: Int){
        _list.removeAll { it.id == id }
        changePosition = position
        currentListState = ListChanges.Delete
        list.postValue(_list)
    }

    fun changeTask(id: Long, text: String, position: Int) = viewModelScope.launch(Dispatchers.IO){
        repository.updateTask(id, text)
        withContext(Dispatchers.Main){
            _list[position].text = text
            currentListState = ListChanges.Edit
            list.postValue(_list)
        }
    }

    fun getChangePosition() = changePosition


}