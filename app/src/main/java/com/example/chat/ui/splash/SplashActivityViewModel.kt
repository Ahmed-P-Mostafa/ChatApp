package com.example.chat.ui.splash

import android.content.Context
import com.example.chat.base.BaseViewModel
import com.example.chat.base.MyApplication
import com.example.chat.util.Constants

class SplashActivityViewModel:BaseViewModel<Navigator>() {

    /*fun isUserLoggedIn(){
        if (deleteUserToSharedPreferences()){
            navigator?.openHome()
        }else{
            navigator?.openLogin()
        }

    }*/
    fun isUserLoggedIn(context: Context){

        val sp = context.getSharedPreferences(Constants.USER_SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)

         if (sp.getBoolean(Constants.IS_USER_SIGNED_IN,false)){
             navigator?.openHome()
         }else{
             navigator?.openLogin()
         }
    }
}