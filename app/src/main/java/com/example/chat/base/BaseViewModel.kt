package com.example.chat.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chat.util.CustomMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

open class BaseViewModel<N>:ViewModel() {
    val auth = Firebase.auth

    var navigator :N? = null
    var dialog = MutableLiveData<CustomMessage>()
    var loader = MutableLiveData<Boolean>()
    val message = MutableLiveData<String>()




}