package com.example.chat.ui.chat

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chat.base.BaseViewModel

class ChatViewModel:BaseViewModel<Navigator>() {
    val input = MutableLiveData<String>()


}