package com.example.chat.ui.home

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chat.base.BaseViewModel
import com.example.chat.onlineDatabase.OnlineDatabase
import com.example.chat.onlineDatabase.group.Group
import com.example.chat.onlineDatabase.group.GroupDao
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentReference

class HomeActivityViewModel:BaseViewModel<Navigator>() {
    private val TAG = "HomeActivityViewModel"
    val name = MutableLiveData<String>()
    val desc = ObservableField<String>()
    val cancelClicked = MutableLiveData(false)
    fun save() {
        Log.d(TAG, "save: ")
        if (isValidData()) {
            GroupDao.addGroupToDatabase(Group(
                name = name.value.toString(),
                desc = desc.get().toString()
            ),
                OnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d(TAG, "isValidData: group crated")
                    } else {
                        Log.d(TAG, "isValidData: failed ${it.exception?.localizedMessage}")
                    }
                })
        } else {
            Log.d(TAG, "isValidData: empty fields")

        }

    }


    fun cancel() {
        Log.d(TAG, "cancel: ")
        cancelClicked.value = true
    }

    fun createNewGroup(group: Group, onCompleteListener: OnCompleteListener<DocumentReference>) {
        GroupDao.addGroupToDatabase(group, onCompleteListener)
    }

    fun logOut() {
        auth.signOut()
        navigator?.openLogin()

    }

    fun isValidData(): Boolean {
        return (!name.value.isNullOrBlank() && !desc.get().isNullOrBlank())
    }

    fun goToChat() {
        navigator?.openChat()
    }
}