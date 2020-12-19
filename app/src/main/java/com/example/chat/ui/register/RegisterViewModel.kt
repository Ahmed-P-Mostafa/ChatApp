package com.example.chat.ui.register

import android.content.DialogInterface
import android.util.Log
import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chat.base.BaseViewModel
import com.example.chat.util.CustomMessage
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel :BaseViewModel<Navigator>() {
    val mAuth :FirebaseAuth = FirebaseAuth.getInstance()
    private val TAG = "RegisterViewModel"


     val name = ObservableField<String>()
     val phone = ObservableField<String>()
     val email = ObservableField<String>()
     val password = ObservableField<String>()

    val nameText = MutableLiveData<Boolean>()
    val phoneText = MutableLiveData<Boolean>()
    val emailText = MutableLiveData<Boolean>()
    val passwordText = MutableLiveData<Boolean>()
    var dataValid = MutableLiveData<Boolean>(false)

    fun isValidData():Boolean{
        if(name.get().isNullOrBlank()) {
            nameText.postValue(false)
        }
        else{
           nameText.postValue(true)
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
        if (name.get().isNullOrBlank()||phone.get().isNullOrBlank()||
            email.get().isNullOrBlank()||password.get().isNullOrBlank()||
            !Patterns.EMAIL_ADDRESS.matcher(email.get().toString()).matches()){
            return false
        }
        return true
    }
    fun signUp(){
        if (isValidData()){
            loader.postValue(true)
            mAuth.createUserWithEmailAndPassword(email.get().toString(),password.get().toString())
                .addOnCompleteListener {
                    loader.postValue(false)
                    Log.d(TAG, "signUp: ")
                    if (it.isSuccessful){
                        dialog.value = CustomMessage(message = "Register Successful",posButton = "ok",posAction = DialogInterface.OnClickListener { dialogInterface, i ->
                            navigator?.openHomeActivity()
                        })
                        val user = mAuth.currentUser
                        Log.d(TAG, "signUp: create user successful"+{user?.email})
                    }else{
                        dialog.value = CustomMessage(message = "Register Successful",posButton = "ok",posAction = DialogInterface.OnClickListener { dialogInterface, i ->
                            dialogInterface.dismiss()
                        })
                        Log.d(TAG, "signUp: create user unsuccessful")
                        Log.d(TAG, "signUp: "+it.exception?.localizedMessage)
                    }
                }

            //navigator?.openHomeActivity()
        }
    }
    fun logIn(){
        navigator?.openLoginActivity()

    }
}