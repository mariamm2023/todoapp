package com.example.todoapp

import android.content.Context
import androidx.core.content.ContextCompat
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class taskitem(
    var name:String,
    var desc:String,
    var duetime:LocalTime?,
    var completedDate: LocalDate?,
    var id:UUID=UUID.randomUUID()
){
    fun iscompleted()=completedDate!=null
    fun imageresorce():Int=if(iscompleted()) R.drawable.uncheck else R.drawable.check
    fun imagecolor(context: Context):Int=if(iscompleted()) purple(context)else black(context)
    fun purple(context: Context)=ContextCompat.getColor(context,R.color.white)
    fun black(context: Context)=ContextCompat.getColor(context,R.color.black)
}