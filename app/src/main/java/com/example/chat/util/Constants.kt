package com.example.chat.util

import com.google.firebase.auth.FirebaseUser

object Constants {

    // home activity intent extra constant
    val GROUP_EXTRA="groupExtra"
    lateinit var USER:FirebaseUser

    // Shared Preferences Keys
    val USER_SHARED_PREFERENCES_FILE_NAME = "user shared preferences name"
    val USER_NAME_KEY = "USER NAME KEY"
    val USER_ID_KEY = "USER ID KEY"
    val USER_PHONE_KEY = "USER PHONE KEY"
    val USER_EMAIL_KEY = "USER EMAIL KEY"
    val USER_PHOTO_KEY = "USER PHOTO KEY"
    val IS_USER_SIGNED_IN = "IS USER SIGNED IN"
    val NULL_VALUE= "NULL"
}