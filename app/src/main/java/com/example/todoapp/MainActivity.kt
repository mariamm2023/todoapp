package com.example.todoapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.databinding.ActivityMainBinding
import android.text.Editable
import android.text.TextWatcher
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity(),taskitemclicklistener {
    lateinit var binding: ActivityMainBinding
    lateinit var taskviewmodel: taskviewmodel1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskviewmodel = ViewModelProvider(this).get(taskviewmodel1::class.java)
        binding.addnewtask.setOnClickListener {
            newtasksheet(null).show(supportFragmentManager, "newtaskTag")
        }
        setrecyclerview()

    }

    private fun setrecyclerview() {
       val mainactivite=this
        taskviewmodel.taskitems.observe(this){
            binding.toolsrecyclerview.apply {
                layoutManager=LinearLayoutManager(applicationContext)
                adapter=taskitemAdapter(it,mainactivite)
            }
        }
    }

    override fun edittaskitem(taskitem: taskitem) {
        newtasksheet(taskitem).show(supportFragmentManager,"newtaskTag")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun completedtaskitem(taskitem: taskitem) {
        taskviewmodel.complated(taskitem)
    }
}