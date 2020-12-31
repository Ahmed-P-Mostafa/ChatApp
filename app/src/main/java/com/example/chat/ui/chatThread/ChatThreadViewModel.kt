package com.example.chat.ui.chatThread

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.chat.base.BaseViewModel
import com.example.chat.models.Message
import com.example.chat.onlineDatabase.OnlineDatabase
import com.example.chat.onlineDatabase.dao.MessageDao
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class ChatThreadViewModel : BaseViewModel<Navigator>() {
    private val TAG = "ChatThreadViewModel"

    var inputText = MutableLiveData<Message>()

fun submit(){

}
    fun addMessage(text:String,groupId:String){
        MessageDao.addMessageToDatabse(Message(text = text,groupId = groupId))
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