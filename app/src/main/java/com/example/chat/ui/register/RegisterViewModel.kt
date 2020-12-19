package com.example.chat.ui.register

import android.content.DialogInterface
import android.util.Log
import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chat.base.BaseViewModel
import com.example.chat.base.CustomMessage
import com.example.chat.onlineDatabase.user.User
import com.example.chat.onlineDatabase.user.UserDao
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener

class RegisterViewModel :BaseViewModel<Navigator>() {

    private val TAG = "RegisterViewModel"


     val firstName = ObservableField<String>()
     val lastName = ObservableField<String>()
     val phone = ObservableField<String>()
     val email = ObservableField<String>()
     val password = ObservableField<String>()

    val firstNameText = MutableLiveData<Boolean>()
    val lastNameText = MutableLiveData<Boolean>()
    val phoneText = MutableLiveData<Boolean>()
    val emailText = MutableLiveData<Boolean>()
    val passwordText = MutableLiveData<Boolean>()
    var dataValid = MutableLiveData(false)

    fun isValidData():Boolean{
        if(firstName.get().isNullOrBlank()) {
            firstNameText.postValue(false)
        }
        else{
           firstNameText.postValue(true)
        }
        if(lastName.get().isNullOrBlank()) {
            lastNameText.postValue(false)
        }
        else{
           lastNameText.postValue(true)
        }
        if (phone.get().isNullOrBlank()){
            phoneText.postValue(false)

        }else{
            phoneText.postValue(true)
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.get().toString()).matches()){
            emailText.postValue(false)

        }else{
            emailText.postValue(true)
        }
        if(password.get().isNullOrBlank()){
            passwordText.postValue(false)
        }else{
            passwordText.postValue(true)
        }
        if (firstName.get().isNullOrBlank()||lastName.get().isNullOrBlank()||phone.get().isNullOrBlank()||
            email.get().isNullOrBlank()||password.get().isNullOrBlank()||
            !Patterns.EMAIL_ADDRESS.matcher(email.get().toString()).matches()){
            return false
        }
        return true
    }
    fun signUp(){
        if (isValidData()){
            loader.postValue(true)
            auth.createUserWithEmailAndPassword(email.get().toString(),password.get().toString())
                .addOnCompleteListener {authTask->
                    loader.postValue(false)
                    Log.d(TAG, "signUp: ")
                    if (authTask.isSuccessful){

                        val user = auth.currentUser
                        addUSerToDatabase(user = User(
                            user?.uid!!,
                            first = firstName.get()!!,
                            last = lastName.get()!!,
                            email = email.get()!!,
                            phone = phone.get()!!
                        ),
                            OnCompleteListener {addUserTask->
                                if (addUserTask.isSuccessful){
                                    Log.d(TAG, "signUp: addUSerToDatabase, OnSuccessListener")
                                    dialog.value = CustomMessage(
                                        message = "add to Database Successful",
                                        posButton = "ok",
                                        posAction = DialogInterface.OnClickListener { dialogInterface, i ->
                                            navigator?.openHomeActivity()
                                        })

                                }else{
                                    message.value = addUserTask.exception?.localizedMessage
                                }

                            })
                        Log.d(TAG, "signUp: create user successful"+{user.email})
                    }else{
                        dialog.value = CustomMessage(message = authTask.exception?.localizedMessage!!,posButton = "ok",posAction = DialogInterface.OnClickListener { dialogInterface, i ->
                            dialogInterface.dismiss()
                        })
                        Log.d(TAG, "signUp: create user unsuccessful")
                        Log.d(TAG, "signUp: "+authTask.exception?.localizedMessage)
                    }
                }
        }
    }
    fun logIn(){
        navigator?.openLoginActivity()

    }
    fun addUSerToDatabase(user:User,onCompleteListener: OnCompleteListener<Void>){
        UserDao.addUserToDatabse(user,onCompleteListener)
    }
}