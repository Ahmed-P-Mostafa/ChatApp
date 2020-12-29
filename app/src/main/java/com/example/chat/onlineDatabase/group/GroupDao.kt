package com.example.chat.onlineDatabase.group

import com.example.chat.onlineDatabase.OnlineDatabase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

object GroupDao {
    fun addGroupToDatabase(group: Group,onCompleteListener: OnCompleteListener<Void>){
        val documentReference = OnlineDatabase.getGroupsReference().document()
                group.id = documentReference.id
        documentReference.set(group).addOnCompleteListener(onCompleteListener)
    }
    fun getAllGroups(onSuccessListener: OnCompleteListener<QuerySnapshot>){
        OnlineDatabase.getGroupsReference().get().addOnCompleteListener(onSuccessListener)
    }
}