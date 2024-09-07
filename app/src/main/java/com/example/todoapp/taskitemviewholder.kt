package com.example.todoapp

import android.content.Context
import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.NewtasksheetBinding
import com.example.todoapp.databinding.TaskitemcellBinding
import java.time.format.DateTimeFormatter

class taskitemviewholder(
    val context: Context,
    val binding: TaskitemcellBinding,
    val clicklistener:taskitemclicklistener
):RecyclerView.ViewHolder(binding.root) {
    @RequiresApi(Build.VERSION_CODES.O)
    val timefomat=DateTimeFormatter.ofPattern("HH::mm")
    @RequiresApi(Build.VERSION_CODES.O)
    fun binftaskitem(taskitem: taskitem){
        binding.name.text=taskitem.name
        if(taskitem.iscompleted()){
            binding.name.paintFlags=Paint.STRIKE_THRU_TEXT_FLAG
            binding.duetime.paintFlags=Paint.STRIKE_THRU_TEXT_FLAG
        }
        binding.completebutton.setColorFilter(taskitem.imagecolor(context))
        binding.completebutton.setImageResource(taskitem.imageresorce())
        binding.completebutton.setOnClickListener {
            clicklistener.completedtaskitem(taskitem)
        }
        binding.taskcontaner.setOnClickListener {
            clicklistener.edittaskitem(taskitem)
        }
        if(taskitem.duetime!=null){
            binding.duetime.text=timefomat.format(taskitem.duetime)

        }
        else{
            binding.duetime.text=" "
        }


    }
}