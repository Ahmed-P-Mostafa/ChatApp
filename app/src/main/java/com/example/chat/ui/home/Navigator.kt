package com.example.chat.ui.home

import com.example.chat.onlineDatabase.models.Group

interface Navigator {
    fun openLogin()
    fun openThread(group: Group)

}