package com.sviatkuzbyt.fasttask.ui.elements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.sviatkuzbyt.fasttask.R
import com.sviatkuzbyt.fasttask.ui.main.MainViewModel

//DELETE LATER
class EditTaskDialogFragment: DialogFragment(R.layout.edit_task) {

    private val viewModel: MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.MyDialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = arguments?.getString("text") ?: ""
        val id = arguments?.getLong("id")?: 0
        val position = arguments?.getInt("position") ?: 0

        val editText = view.findViewById<EditText>(R.id.editTaskText)
        editText.setText(text)

        val deleteButton = view.findViewById<Button>(R.id.deleteButton)
        deleteButton.setOnClickListener {
            viewModel.deleteTask(id, position)
            dismiss()
        }

        val saveButton = view.findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            viewModel.changeTask(id, editText.text.toString(), position)
            dismiss()
        }

        val backButton = view.findViewById<View>(R.id.backButton)
        backButton.setOnClickListener {
            dismiss()
        }
    }
}