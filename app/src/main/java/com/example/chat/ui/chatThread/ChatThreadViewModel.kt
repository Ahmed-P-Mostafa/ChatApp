package com.example.chat.ui.chatThread

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.chat.base.BaseViewModel
import com.example.chat.models.Message
import com.example.chat.onlineDatabase.OnlineDatabase
import com.example.chat.onlineDatabase.dao.MessageDao
import com.example.chat.onlineDatabase.models.User
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class ChatThreadViewModel : BaseViewModel<Navigator>() {
    private val TAG = "ChatThreadViewModel"

    var inputText = MutableLiveData<Message>()


    fun addMessage(text:String,groupId:String,context: Context){
        MessageDao.addMessageToDatabse(Message(text = text,groupId = groupId,user = getUserFromSharedPreferences(context)))
    }
    fun listenForMessagesUpdate(groupId: String){

        MessageDao.listenForMessagesUpdate(groupId, EventListener { value, error ->
            if (error != null) {
                Log.d(TAG, "listenForMessagesUpdate: listen Error")
                return@EventListener
            }
            for (dc in value?.documentChanges!!){
                when(dc.type){

                    DocumentChange.Type.ADDED->
                            this.inputText.value = dc.document.toObject(Message::class.java)

                }
            }

        })
    }

}