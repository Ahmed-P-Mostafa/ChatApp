package com.example.chat.onlineDatabase.models

import com.example.chat.util.Constants
import com.stfalcon.chatkit.commons.models.IUser

class User(
    private val id: String?=null,
    val first: String?=null,
    val last: String?=null,
    val email: String?=null,
    val phone: String?=null,
    val picture:String?=null,
    var isOnline:Boolean?=null):IUser {

    override fun getId(): String {
        return id!!
    }

    override fun getName(): String {
        return first!!
    }

    override fun getAvatar(): String {
        return picture?:""
    }
    fun isUserOnline():Boolean{
        return isOnline?:false
    }

}