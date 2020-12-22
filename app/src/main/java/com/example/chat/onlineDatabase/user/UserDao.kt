package com.example.chat.onlineDatabase.user

import com.example.chat.onlineDatabase.OnlineDatabase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object UserDao {

    fun addUserToDatabse(user: User,onCompleteListener: OnCompleteListener<Void>){
        OnlineDatabase.getUsersReference().document(user.id).set(user).addOnCompleteListener(onCompleteListener)
    }
}