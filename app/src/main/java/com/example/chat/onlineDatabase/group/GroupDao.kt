package com.example.chat.onlineDatabase.group

import com.example.chat.onlineDatabase.OnlineDatabase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference

object GroupDao {
    fun addGroupToDatabase(group: Group,onCompleteListener: OnCompleteListener<DocumentReference>){
        OnlineDatabase.getGroupsReference().add(group).addOnCompleteListener(onCompleteListener)
    }
}