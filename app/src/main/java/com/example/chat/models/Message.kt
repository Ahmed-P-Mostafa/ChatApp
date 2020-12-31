package com.example.chat.models

import android.media.Image
import android.speech.tts.Voice
import com.example.chat.onlineDatabase.models.User
import com.example.chat.util.Constants
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.util.*


 class Message() : IMessage {
     constructor( text: String? = null,groupId:String) : this()

     private var text: String? = null
     private var id: String? = null
     private var groupId:String?=null
     private var createdAt: Date= Date()
     private var image: Image? = null
     private var voice: Voice? = null

    override fun getId(): String {
            return id?:""
        }

        override fun getText(): String {
            return text?:""
        }

        override fun getUser(): IUser = object : IUser {

            override fun getId()=Constants.USER.uid

            override fun getName()=Constants.USER.displayName

            override fun getAvatar()=Constants.USER.photoUrl.toString()


        }

        override fun getCreatedAt(): Date {
            return createdAt
        }
    fun setId(id:String){
        this.id = id
    }
    fun getGroupId(): String? {
        return groupId
    }
}
