package com.example.chat.ui.new_group_dialog

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chat.base.BaseViewModel
interface Navigator{

}
class DialogViewModel:BaseViewModel<Navigator>() {

    val name = ObservableField<String>()
    val desc = ObservableField<String>()
    val saveClicked = MutableLiveData(false)
    val cancelClicked = MutableLiveData(false)
    fun save(){
        saveClicked.value = true
    }
    fun cancel(){
        cancelClicked.value = true
    }

}