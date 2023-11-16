package com.sviatkuzbyt.fasttask.ui.recycler

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sviatkuzbyt.fasttask.R
import com.sviatkuzbyt.fasttask.data.elements.UnDoneTask
import com.sviatkuzbyt.fasttask.ui.main.MainViewModel

class TaskAdapter(private var viewModel: MainViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list = mutableListOf<UnDoneTask>()
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_EMPTY = 1
    class TaskHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textTask: TextView = view.findViewById(R.id.textTask)
        val doneButton: View = view.findViewById(R.id.doneButton)
    }

    class EmptyHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                VIEW_TYPE_ITEM -> {
                    val view = LayoutInflater.from(viewGroup.context)
                        .inflate(R.layout.task_item, viewGroup, false)
                    TaskHolder(view)
                }
                VIEW_TYPE_EMPTY -> {
                    val view = LayoutInflater.from(viewGroup.context)
                        .inflate(R.layout.no_tasks, viewGroup, false)
                    EmptyHolder(view)
                }
                else -> throw IllegalArgumentException("Invalid view type")
            }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when(viewHolder){
            is TaskHolder -> {
                viewHolder.textTask.text = list[position].text
                viewHolder.doneButton.setBackgroundResource(R.drawable.circle)

                viewHolder.itemView.setOnClickListener {
                    Log.v("appTest", position.toString())
                }

                viewHolder.doneButton.setOnClickListener {
                    viewModel.removeItem(list[position].id)
                    removeElement(position)
                }
            }
            is EmptyHolder -> {}
        }



    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(_list: MutableList<UnDoneTask>){
        list = _list
        notifyDataSetChanged()
    }

    fun addElement(){
        if(list.size==1){
            notifyItemRemoved(0)
            notifyItemChanged(0)
        }
        notifyItemInserted(0)
        notifyItemRangeChanged(0, list.size)
    }

    private fun removeElement(position: Int){
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size-position)
    }

    override fun getItemCount(): Int {
        return if (list.isEmpty()) {
            1 // Display the empty state view
        } else {
            list.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list.isEmpty()) {
            VIEW_TYPE_EMPTY
        } else {
            VIEW_TYPE_ITEM
        }
    }
}