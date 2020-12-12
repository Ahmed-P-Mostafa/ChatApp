package com.example.chat.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.example.chat.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class LoginViewModel:BaseViewModel<Navigator>() {
    private val TAG = "LoginViewModel"

    val auth = FirebaseAuth.getInstance()
    var isSignedLiveData =  MutableLiveData<Boolean>()

     var email = MutableStateFlow("")
     var password = MutableStateFlow("")




    fun isSignedIn(){
        isSignedLiveData.value = auth.currentUser != null
    }
    fun logIn(){
        Log.d(TAG, "validateLogin: ")
        

    }
    fun setEmail(email:String){

        this.email.value = email
    }

    fun setPasword(password:String){
        this.password.value = password
    }
    val isLoginEnabled: Flow<Boolean> = combine(email, password) { email, password ->
        val isEmailCorrect = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordCorrect = password.length > 7
        return@combine isEmailCorrect and isPasswordCorrect
    }
    fun goToRegister(){
        navigator?.openRegister()

    }

    
}