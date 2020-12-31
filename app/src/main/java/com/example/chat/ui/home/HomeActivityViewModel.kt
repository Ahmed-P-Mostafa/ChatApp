package com.example.chat.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.chat.base.BaseViewModel
import com.example.chat.base.MyApplication
import com.example.chat.onlineDatabase.models.Group
import com.example.chat.onlineDatabase.dao.GroupDao
import com.example.chat.util.Constants
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseUser

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


     fun logOut(context: Context) {
        auth.signOut()
         deleteUserToSharedPreferences(context)
       /* suspend {
            MyApplication().removeUserFromDataStore()
        }*/

        navigator?.openLogin()

    }
    fun deleteUserToSharedPreferences(context: Context){
        // add Firebase user to shared preferences to validate on it if the user is signed in or logged out

        val sp = context.getSharedPreferences(
            Constants.USER_SHARED_PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE
        )
        val editor : SharedPreferences.Editor = sp.edit()
        editor.putString(Constants.USER_NAME_KEY,Constants.NULL_VALUE)
        editor.putString(Constants.USER_ID_KEY,Constants.NULL_VALUE)
        editor.putString(Constants.USER_EMAIL_KEY,Constants.NULL_VALUE)
        editor.putString(Constants.USER_PHONE_KEY,Constants.NULL_VALUE)
        editor.putString(Constants.USER_PHOTO_KEY,Constants.NULL_VALUE)
        editor.putBoolean(Constants.IS_USER_SIGNED_IN,false)
        editor.apply()
    }


}