package com.example.chat.ui.login

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.example.chat.base.BaseViewModel
import com.example.chat.util.Constants
import com.example.chat.util.CustomMessage
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class LoginViewModel:BaseViewModel<Navigator>() {
    private val TAG = "fireBase LoginViewModel"


    var isUserLoggedIn = MutableLiveData<Boolean>()
    private val webClientId = "611522890597-e6dc2r9ld6s6vj014pn36camn5aoil3l.apps.googleusercontent.com"
    private val webClientSecret = "7hDV_hLKjDHwUXSiQJ59-Gav"


    // Configure Google Sign In
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(webClientId)
        .requestEmail()
        .build()



     var email = MutableStateFlow("")
     var password = MutableStateFlow("")




    /*fun isSignedIn(){

        isUserLoggedIn.value = auth.currentUser != null
        //message.value=auth.currentUser?.email
    }*/
    fun logIn(){
        loader.postValue(true)

        Log.d(TAG, "logIn: ")

        auth.signInWithEmailAndPassword(email.value.toString(),password.value.toString()).addOnCompleteListener(
            OnCompleteListener {
                loader.postValue(false)
                if (it.isSuccessful) {
                    //saveUserToSharedPreferences(context,it.result?.user!!)

                    Log.d(TAG, "logIn: login is successful")
                    val user: FirebaseUser? = auth.currentUser
                    //Constants.USER = auth.currentUser!!
                    Log.d(TAG, "logIn: ${user?.uid}")
                    // update UI
                    dialog.value= CustomMessage(message = "Login Successfully",posButton = "ok",posAction =  { dialogInterface, i ->
                        navigator?.openHome()
                    })
                } else {
                    Log.d(TAG, "logIn: login failure ${it.exception}")
                    // update Ui
                    message.value="login failure"
                }

            })

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

                //saveUserToSharedPreferences(context,it.result?.user!!)

                Constants.USER= it.result?.user!!
                Log.d(TAG, "firebaseAuthWithGoogle: authorization successful")
                Log.d(TAG, "firebaseAuthWithGoogle: ${auth.currentUser?.email}")
                dialog.value = CustomMessage(message = "login Successfully",posButton = "ok",posAction ={ dialogInterface, i ->
                    navigator?.openHome()
                })
            }else{
                Log.d(TAG, "firebaseAuthWithGoogle: ${it.exception}")
                message.value="login with google failure"
            }

        }.addOnFailureListener{
            Log.d(TAG, "firebaseAuthWithGoogle: authorization failed")
        }
    }
    /*fun saveUserToSharedPreferences(context: Context,user:FirebaseUser){
        // add Firebase user to shared preferences to validate on it if the user is signed in or logged out
        val sp = context.getSharedPreferences(Constants.USER_SHARED_PREFERENCES_FILE_NAME,MODE_PRIVATE)
        val editor :SharedPreferences.Editor = sp.edit()
        editor.putString(Constants.USER_NAME_KEY,user.displayName)
        editor.putString(Constants.USER_ID_KEY,user.uid)
        editor.putString(Constants.USER_EMAIL_KEY,user.email)
        editor.putString(Constants.USER_PHONE_KEY,user.phoneNumber)
        editor.putString(Constants.USER_PHOTO_KEY,user.photoUrl.toString())
        editor.putBoolean(Constants.IS_USER_SIGNED_IN,true)
        editor.apply()
    }*/


    
}