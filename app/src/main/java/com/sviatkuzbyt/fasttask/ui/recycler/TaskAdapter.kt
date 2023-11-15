package com.sviatkuzbyt.fasttask.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sviatkuzbyt.fasttask.R
import com.sviatkuzbyt.fasttask.data.elements.UnDoneTask


class TaskAdapter(private val list: MutableList<UnDoneTask>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textTask: TextView = view.findViewById(R.id.textTask)
        val doneButton: View = view.findViewById(R.id.doneButton)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.task_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textTask.text = list[position].text
        viewHolder.doneButton.setBackgroundResource(R.drawable.circle)
    }

    override fun getItemCount() = list.size
}