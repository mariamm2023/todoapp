package com.example.todoapp

import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.databinding.NewtasksheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime


class newtasksheet(var taskitem: taskitem?) : BottomSheetDialogFragment() {
    lateinit var bindind:NewtasksheetBinding
    lateinit var taskviewmodel:taskviewmodel1
    private var duetime:LocalTime?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindind= NewtasksheetBinding.inflate(layoutInflater)
        return bindind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        if(taskitem!=null){
            bindind.tasktitle.text="Edit task"
            val editable=Editable.Factory.getInstance()
            bindind.Name.text=editable.newEditable(taskitem!!.name)
            bindind.desc.text=editable.newEditable(taskitem!!.desc)
            if(taskitem!!.duetime!=null){
                duetime=taskitem!!.duetime!!
                updatestimedutton()

            }
        }
        else{
            bindind.tasktitle.text="New task"
        }
        taskviewmodel=ViewModelProvider(activity).get(taskviewmodel1::class.java)
        bindind.save.setOnClickListener {
            saveaction()
        }
        bindind.timerbutton.setOnClickListener {
            opentimerpicker()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun opentimerpicker() {
        if(duetime==null){
            duetime= LocalTime.now()
            val listener=TimePickerDialog.OnTimeSetListener { _, selectedhour, selectedminute ->
                duetime= LocalTime.of(selectedhour,selectedminute)
                updatestimedutton()
            }
            val dialog=TimePickerDialog(activity,listener,duetime!!.hour,duetime!!.minute,true)
            dialog.setTitle("task due")
            dialog.show()

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updatestimedutton() {
        bindind.timerbutton.text= String.format("%02d:%02d",duetime!!.hour,duetime!!.minute)
    }

    private fun saveaction() {
        val name=bindind.Name.text.toString()
       val desc=bindind.desc.text.toString()
        if (taskitem==null){
            val newtask=taskitem(name,desc,duetime,null)
            taskviewmodel.addtaskitem(newtask)
        }
        else{
            taskviewmodel.updatetaskitem(taskitem!!.id,name,desc,duetime)

        }
        bindind.Name.setText(" " )
        bindind.desc.setText(" ")
        dismiss()
    }

}