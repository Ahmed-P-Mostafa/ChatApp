package com.example.chat.ui.home

import com.example.chat.base.BaseViewModel
import com.example.chat.onlineDatabase.OnlineDatabase
import com.example.chat.onlineDatabase.group.Group
import com.example.chat.onlineDatabase.group.GroupDao
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference

class HomeActivityViewModel:BaseViewModel<Navigator>() {

    fun createNewGroup(group: Group, onCompleteListener: OnCompleteListener<DocumentReference>) {
        GroupDao.addGroupToDatabase(group, onCompleteListener)
    }
    fun logOut() {
        auth.signOut()
        message.value = auth.currentUser?.email

    }
}