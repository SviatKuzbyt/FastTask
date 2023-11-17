package com.sviatkuzbyt.fasttask.ui.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.sviatkuzbyt.fasttask.R
import com.sviatkuzbyt.fasttask.data.elements.UnDoneTask
import com.sviatkuzbyt.fasttask.ui.elements.EditTaskDialogFragment
import com.sviatkuzbyt.fasttask.ui.main.MainViewModel

class TaskAdapter(private var list: MutableList<UnDoneTask>, private var viewModel: MainViewModel, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
                TaskHolder(LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.task_item, viewGroup, false))
            }
            VIEW_TYPE_EMPTY -> {
                EmptyHolder(LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.no_tasks, viewGroup, false))
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list.isEmpty()) VIEW_TYPE_EMPTY
        else VIEW_TYPE_ITEM
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if(viewHolder is TaskHolder){
            viewHolder.textTask.text = list[position].text
            viewHolder.doneButton.setBackgroundResource(R.drawable.circle)

            viewHolder.doneButton.setOnClickListener {
                viewModel.makeDoneTask(list[position].id, position)
            }

            viewHolder.itemView.setOnClickListener {
                val fragmentManager = (context as FragmentActivity).supportFragmentManager
                val dialogFragment = EditTaskDialogFragment()
                dialogFragment.arguments = bundleOf(
                    "id" to list[position].id,
                    "text" to list[position].text,
                    "position" to position)
                dialogFragment.show(fragmentManager, "change_task")
            }
        }
    }

    fun addElement(){
        if(list.size==1)
            notifyItemRemoved(0)
        notifyItemInserted(0)
        notifyItemRangeChanged(0, itemCount)
    }

    fun removeElement(position: Int){
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount-position)
    }

    override fun getItemCount(): Int {
        return if (list.isEmpty()) 1
        else list.size
    }
}