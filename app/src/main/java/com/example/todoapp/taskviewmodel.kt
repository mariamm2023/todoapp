package com.example.todoapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class taskviewmodel1:ViewModel()
{
    var taskitems=MutableLiveData<MutableList<taskitem>>()
    init {
        taskitems.value= mutableListOf()
    }
    fun addtaskitem(newtask:taskitem){
        val list=taskitems.value
        list!!.add(newtask)
        taskitems.postValue(list)

    }
    fun updatetaskitem(id:UUID,name:String,desc:String,duetime: LocalTime?){
        val list=taskitems.value
        val task=list!!.find {it.id==id}!!
        task.name=name
        task.desc=desc
        if (duetime != null) {
            task.duetime=duetime
        }
        taskitems.postValue(list)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun complated(taskitem: taskitem){
        val list=taskitems.value
        val task=list!!.find { it.id==taskitem.id }!!
        if(task.completedDate==null)
            task.completedDate= LocalDate.now()
        taskitems.postValue(list)
    }


}