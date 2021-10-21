package com.example.chat.onlineDatabase.dao

import com.example.chat.models.Message
import com.example.chat.onlineDatabase.OnlineDatabase
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

object MessageDao {
    fun addMessageToDatabse(message:Message){
        val docRef = OnlineDatabase.getMessagesRef(message.getGroupId()!!).document()
        message.id = docRef.id
        docRef.set(message)
    }
    fun listenForMessagesUpdate(groupId:String,eventListener: EventListener<QuerySnapshot>){
        OnlineDatabase.getMessagesRef(groupId = groupId,).orderBy("createdAt").limitToLast(100).addSnapshotListener(eventListener)
    }
}