package com.example.chat.ui.login

import android.app.Application
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.base.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.channels.consumesAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class LoginViewModel:BaseViewModel<Navigator>() {
    private val TAG = "fireBase LoginViewModel"

    val auth = FirebaseAuth.getInstance()
    var isSignedLiveData =  MutableLiveData<Boolean>()
    private val webClientId = "611522890597-e6dc2r9ld6s6vj014pn36camn5aoil3l.apps.googleusercontent.com"
    private val webClientSecret = "7hDV_hLKjDHwUXSiQJ59-Gav"


    // Configure Google Sign In
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(webClientId)
        .requestEmail()
        .build()



     var email = MutableStateFlow("")
     var password = MutableStateFlow("")




    fun isSignedIn(){

        isSignedLiveData.value = auth.currentUser != null
        message.postValue(auth.currentUser?.email)
    }
    fun logIn(){
        loader.postValue(true)

        Log.d(TAG, "logIn: ")

        auth.signInWithEmailAndPassword(email.value.toString(),password.value.toString()).addOnCompleteListener(
            OnCompleteListener {
                loader.postValue(false)
                if (it.isSuccessful) {
                    Log.d(TAG, "logIn: login is successful")
                    val user: FirebaseUser? = auth.currentUser
                    Log.d(TAG, "logIn: ${user?.uid}")
                    // update UI
                    message.postValue("login successful")
                } else {
                    Log.d(TAG, "logIn: login failure ${it.exception}")
                    // update Ui
                    message.postValue("login failure")
                }

            })

    }
    fun logOut(){
        auth.signOut()
        message.postValue(auth.currentUser?.email)
    }

    fun setEmail(email:String){

        this.email.value = email
    }

    fun setPasword(password:String){
        this.password.value = password
    }
    val isLoginEnabled: Flow<Boolean> = combine(email, password) { email, password ->
        val isEmailCorrect = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordCorrect = password.length > 7
        return@combine isEmailCorrect and isPasswordCorrect
    }
    fun goToRegister(){
        navigator?.openRegister()

    }
    fun loginWithGoogle(){
        //loader.postValue(true)
        navigator?.openGoogleIntent()


    }
    fun firebaseAuthWithGoogle(idToken:String){
        loader.postValue(true)
        Log.d(TAG, "firebaseAuthWithGoogle: ")
        val credential = GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            loader.postValue(false)
            Log.d(TAG, "firebaseAuthWithGoogle: authorization completed")
            if (it.isSuccessful){
                Log.d(TAG, "firebaseAuthWithGoogle: authorization successful")
                Log.d(TAG, "firebaseAuthWithGoogle: ${auth.currentUser?.email}")
                message.postValue("login with google successful")
            }else{
                Log.d(TAG, "firebaseAuthWithGoogle: ${it.exception}")
                message.postValue("login with google failure")
            }

        }.addOnFailureListener{
            Log.d(TAG, "firebaseAuthWithGoogle: authorization failed")
        }
    }


    
}