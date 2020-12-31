package com.example.chat.models

import android.media.Image
import android.speech.tts.Voice
import com.example.chat.onlineDatabase.models.User
import com.example.chat.util.Constants
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.util.*


 data class Message(private var text: String? = null, private var id: String? = null,
                    private var groupId: String? = null, private var createdAt: Date? = Date(),
                    private var user: User?=null, private var image: Image? = null,
                    private var voice: Voice? = null) : IMessage {


     override fun getId(): String {
            return id?:""
        }

        override fun getText(): String {
            return text?:""
        }

        override fun getUser(): IUser = object : IUser {

            override fun getId()=user?.id

            override fun getName()=user?.name

            override fun getAvatar()=user?.avatar


        }
        override fun getCreatedAt(): Date {
            return createdAt!!
        }
    fun setId(id:String){
        this.id = id
    }
    fun getGroupId(): String? {
        return groupId
    }
}
