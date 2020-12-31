package com.example.chat.onlineDatabase.dao

import com.example.chat.onlineDatabase.OnlineDatabase
import com.example.chat.onlineDatabase.models.Group
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.QuerySnapshot

object GroupDao {
    fun addGroupToDatabase(group: Group, onCompleteListener: OnCompleteListener<Void>){
        val documentReference = OnlineDatabase.getGroupsReference().document()
                group.id = documentReference.id
        documentReference.set(group).addOnCompleteListener(onCompleteListener)
    }
    fun getAllGroups(onSuccessListener: OnCompleteListener<QuerySnapshot>){
        OnlineDatabase.getGroupsReference().get().addOnCompleteListener(onSuccessListener)
    }
}