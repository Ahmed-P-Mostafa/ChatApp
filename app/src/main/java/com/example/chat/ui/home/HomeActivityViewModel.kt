package com.example.chat.ui.home

import com.example.chat.base.BaseViewModel

class HomeActivityViewModel:BaseViewModel<Navigator>() {

    fun logOut(){
        auth.signOut()
        message.value = auth.currentUser?.email
    }
}