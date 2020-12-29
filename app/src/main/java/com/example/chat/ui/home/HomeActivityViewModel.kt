package com.example.chat.ui.home

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chat.adapters.ChatsAdapter
import com.example.chat.base.BaseViewModel
import com.example.chat.onlineDatabase.OnlineDatabase
import com.example.chat.onlineDatabase.group.Group
import com.example.chat.onlineDatabase.group.GroupDao
import com.example.chat.util.ChatModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference

class HomeActivityViewModel:BaseViewModel<Navigator>() {
    private val TAG = "HomeActivityViewModel"

    var list = MutableLiveData<List<Group>>()


    fun getGroups(){
        Log.d(TAG, "getGroups: started")
        GroupDao.getAllGroups(OnCompleteListener {
            val list = mutableListOf<Group>()
            if (it.isSuccessful) {
                Log.d(TAG, "getGroups: task succeed")
                for (document in it.result!!.documents) {
                    val group = document.toObject(Group::class.java)


                    Log.d(TAG, "getGroups: ${document}")
                    if (group == null) continue
                    list.add(group)
                }
                this.list.value = list
                Log.d(TAG, "getGroups: live data list size ${(this.list.value as MutableList<Group>).size}")
                Log.d(TAG, "getGroups:  list size ${list.size}")
            }

        })
    }


    fun logOut() {
        auth.signOut()
        navigator?.openLogin()

    }

}