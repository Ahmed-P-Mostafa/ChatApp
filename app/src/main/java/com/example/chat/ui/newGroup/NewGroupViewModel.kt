package com.example.chat.ui.newGroup

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chat.base.BaseViewModel
import com.example.chat.onlineDatabase.group.Group
import com.example.chat.onlineDatabase.group.GroupDao
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.getField

class NewGroupViewModel:BaseViewModel<Navigator>() {
    private val TAG = "NewGroupViewModel"
    val name = MutableLiveData<String>()
    val desc = ObservableField<String>()
    var groupNameLiveData = MutableLiveData<String>()

    fun save() {
        Log.d(TAG, "save: ")
        if (isValidData()) {
            createNewGroup(Group(
                name = name.value.toString(),
                desc = desc.get().toString()
            ), OnCompleteListener {

                    if (it.isSuccessful) {
                        Log.d(TAG, "isValidData: group crated")
                        navigator?.goBack()

                    } else {
                        Log.d(TAG, "isValidData: failed ${it.exception?.localizedMessage}")
                    }
                })
        } else {
            Log.d(TAG, "isValidData: empty fields")
        }
    }

    private fun createNewGroup(group: Group, onCompleteListener: OnCompleteListener<Void>) {
        GroupDao.addGroupToDatabase(group, onCompleteListener)
    }


    private fun isValidData(): Boolean {
        return (!name.value.isNullOrBlank() && !desc.get().isNullOrBlank())
    }
}