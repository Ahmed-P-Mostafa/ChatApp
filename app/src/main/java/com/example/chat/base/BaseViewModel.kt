package com.example.chat.base

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chat.onlineDatabase.models.User
import com.example.chat.util.Constants
import com.example.chat.util.CustomMessage
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

open class BaseViewModel<N>:ViewModel() {
    val auth = Firebase.auth

    var navigator :N? = null
    var dialog = MutableLiveData<CustomMessage>()
    var loader = MutableLiveData<Boolean>()
    val message = MutableLiveData<String>()

    fun saveUserToSharedPreferences(context: Context, user: FirebaseUser){
       // add Firebase user to shared preferences to validate on it if the user is signed in or logged out
       val sp = context.getSharedPreferences(Constants.USER_SHARED_PREFERENCES_FILE_NAME,MODE_PRIVATE)
       val editor :SharedPreferences.Editor = sp.edit()
       editor.putString(Constants.USER_NAME_KEY,user.displayName)
       editor.putString(Constants.USER_ID_KEY,user.uid)
       editor.putString(Constants.USER_EMAIL_KEY,user.email)
       editor.putString(Constants.USER_PHONE_KEY,user.phoneNumber)
       editor.putString(Constants.USER_PHOTO_KEY,user.photoUrl.toString())
       editor.putBoolean(Constants.IS_USER_SIGNED_IN,true)
       editor.apply()
   }
    fun getUserFromSharedPreferences(context: Context):User{
        val sp = context.getSharedPreferences(Constants.USER_SHARED_PREFERENCES_FILE_NAME, MODE_PRIVATE)
        val userId = sp.getString(Constants.USER_ID_KEY,Constants.NULL_VALUE)
        val userName = sp.getString(Constants.USER_NAME_KEY,Constants.NULL_VALUE)
        val userEmail = sp.getString(Constants.USER_EMAIL_KEY,Constants.NULL_VALUE)
        val userPhoto = sp.getString(Constants.USER_PHOTO_KEY,Constants.NULL_VALUE)

        return User(id = userId!!,first = userName!!,email = userEmail!!,picture = userPhoto!!)
    }




}