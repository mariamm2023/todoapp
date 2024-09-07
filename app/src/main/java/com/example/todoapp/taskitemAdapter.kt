package com.example.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.TaskitemcellBinding

class taskitemAdapter(val taskitems:List<taskitem>,
                      val clicklistener:taskitemclicklistener

):RecyclerView.Adapter<taskitemviewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskitemviewholder {
       val from=LayoutInflater.from(parent.context)
        val binding=TaskitemcellBinding.inflate(from,parent,false)
        return taskitemviewholder(parent.context,binding,clicklistener)
    }

    override fun getItemCount()=taskitems.size



    override fun onBindViewHolder(holder: taskitemviewholder, position: Int) {
holder.binftaskitem(taskitems[position])
    }
}