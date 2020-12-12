package com.example.chat.ui.register

import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chat.base.BaseViewModel

class RegisterViewMdel :BaseViewModel<Navigator>() {


     val name = ObservableField<String>()
     val phone = ObservableField<String>()
     val email = ObservableField<String>()
     val password = ObservableField<String>()

    val nameText = MutableLiveData<Boolean>()
    val phoneText = MutableLiveData<Boolean>()
    val emailText = MutableLiveData<Boolean>()
    val passwordText = MutableLiveData<Boolean>()
    var dataValid = MutableLiveData<Boolean>(false)

    fun isValidData():Boolean{
        if(name.get().isNullOrBlank()) {
            nameText.postValue(false)
        }
        else{
           nameText.postValue(true)
        }
        if (phone.get().isNullOrBlank()){
            phoneText.postValue(false)

        }else{
            phoneText.postValue(true)
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.get().toString()).matches()){
            emailText.postValue(false)

        }else{
            emailText.postValue(true)
        }
        if(password.get().isNullOrBlank()){
            passwordText.postValue(false)
        }else{
            passwordText.postValue(true)
        }
        if (name.get().isNullOrBlank()||phone.get().isNullOrBlank()||
            email.get().isNullOrBlank()||password.get().isNullOrBlank()||
            !Patterns.EMAIL_ADDRESS.matcher(email.get().toString()).matches()){
            return false
        }
        return true
    }
    fun signUp(){
        if (isValidData()){
            navigator?.openHomeActivity()
        }
    }
    fun logIn(){
        navigator?.openLoginActivity()

    }
}