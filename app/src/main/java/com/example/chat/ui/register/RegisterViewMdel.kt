package com.example.chat.ui.register

import androidx.databinding.ObservableField
import com.example.chat.base.BaseViewModel

class RegisterViewMdel :BaseViewModel<Navigator>() {

    val name = ObservableField<String>()
    val phone = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    fun isValidData():Boolean{
        if(name.get().isNullOrBlank()|| phone.get().isNullOrBlank()||email.get().isNullOrBlank()||password.get().isNullOrBlank())return false

        return true
    }
    fun signUp(){
        if (isValidData()){
            navigator
        }
    }
}