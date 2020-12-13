package com.example.chat.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

open class BaseViewModel<N>:ViewModel() {
    var navigator :N? = null
    var message = MutableLiveData<String>()
    var loader = MutableLiveData<Boolean>()



}