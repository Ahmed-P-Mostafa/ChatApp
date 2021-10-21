package com.example.chat.onlineDatabase

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object OnlineDatabase {
    private val USER_REF="users"
    private val GROUP_REF="groups"
    private val Messages_REF="messages"
    val firestore = Firebase.firestore
    fun getUsersReference():CollectionReference{
        return firestore.collection(USER_REF)
    }
    fun getGroupsReference():CollectionReference{
        return firestore.collection(GROUP_REF)
    }
    fun getMessagesRef(groupId:String):CollectionReference{
        return firestore.collection(GROUP_REF).document(groupId).collection(Messages_REF)
    }

}