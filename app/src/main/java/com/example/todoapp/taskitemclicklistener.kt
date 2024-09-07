package com.example.todoapp

interface taskitemclicklistener {
    fun edittaskitem(taskitem: taskitem)
    fun completedtaskitem(taskitem: taskitem)
}