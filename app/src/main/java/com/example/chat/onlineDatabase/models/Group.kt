package com.example.chat.onlineDatabase.models

import java.io.Serializable

data class Group(val picture: String? = null,
                   var id: String?=null,
                   var desc: String? = null,
                   var name: String? = null

  ):Serializable
