package com.example.spinnerapplication

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var taskInput: EditText
    private lateinit var prioritySpinner: Spinner
    private lateinit var addTaskButton: Button
    private lateinit var todoList: ListView
    private lateinit var todoListAdapter: ArrayAdapter<String>
    private val tasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskInput = findViewById(R.id.task_input)
        prioritySpinner = findViewById(R.id.priority_spinner)
        addTaskButton = findViewById(R.id.add_task_button)
        todoList = findViewById(R.id.todo_list)

        setupPrioritySpinner()
        setupAddTaskButton()
        setupTodoList()
    }

    private fun setupPrioritySpinner() {
        val priorities = arrayOf("High", "Medium", "Low")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, priorities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        prioritySpinner.adapter = adapter

        prioritySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // No action needed for now
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No action needed for now
            }
        }
    }

    private fun setupAddTaskButton() {
        addTaskButton.setOnClickListener {
            val task = taskInput.text.toString()
            val priority = prioritySpinner.selectedItem.toString()

            if (task.isNotEmpty()) {
                tasks.add("$task - Priority: $priority")
                todoListAdapter.notifyDataSetChanged()
                taskInput.text.clear()
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupTodoList() {
        todoListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasks)
        todoList.adapter = todoListAdapter
    }
}
