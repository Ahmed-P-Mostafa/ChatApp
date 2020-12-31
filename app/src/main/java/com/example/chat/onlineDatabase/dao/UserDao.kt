package com.example.chat.onlineDatabase.dao

import com.example.chat.onlineDatabase.OnlineDatabase
import com.example.chat.onlineDatabase.models.User
import com.google.android.gms.tasks.OnCompleteListener

object UserDao {

    fun addUserToDatabse(user: User, onCompleteListener: OnCompleteListener<Void>){
        OnlineDatabase.getUsersReference().document(user.id).set(user).addOnCompleteListener(onCompleteListener)
    }
}