package com.example.chat.onlineDatabase.models

import com.example.chat.util.Constants
import com.stfalcon.chatkit.commons.models.IUser

class User(
    private val id: String,
    val first: String,
    val last: String,
    val email: String,
    val phone: String,
    val picture:String?=null,
    var isOnline:Boolean?=null):IUser {

    override fun getId(): String {
        return Constants.USER.uid
    }

    override fun getName(): String {
        return first
    }

    override fun getAvatar(): String {
        return picture?:""
    }
    fun isOnline():Boolean{
        return isOnline?:false
    }

}