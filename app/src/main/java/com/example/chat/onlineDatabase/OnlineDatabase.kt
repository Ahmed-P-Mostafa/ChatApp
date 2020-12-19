package com.example.chat.onlineDatabase

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object OnlineDatabase {
    private val USER_REF="users"
    private val GROUP_REF="groups"
    val firestore = Firebase.firestore
    fun getUsersReference():CollectionReference{
        return firestore.collection(USER_REF)
    }
    fun getGroupsReference():CollectionReference{
        return firestore.collection(GROUP_REF)
    }
}