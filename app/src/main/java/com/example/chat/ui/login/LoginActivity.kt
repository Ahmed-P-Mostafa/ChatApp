package com.example.chat.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityLoginBinding
import com.example.chat.ui.home.HomeActivity
import com.example.chat.ui.register.RegisterActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.flow.collect

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(),Navigator {
    private val TAG = "LoginActivity"
    private val RC_SIGN_IN = 9001

    lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this

        dataBinding.vm = viewModel

        googleSignInClient = GoogleSignIn.getClient(this,viewModel.gso)


        dataBinding.userNameEditText.addTextChangedListener{
            viewModel.setEmail(it.toString())
        }
        dataBinding.passwordEditText.addTextChangedListener{
            viewModel.setPasword(it.toString())
        }

        lifecycleScope.launchWhenCreated {
            viewModel.isLoginEnabled.collect {
                dataBinding.loginButton.enabled(it)
            }
        }


    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initializeViewModel()=LoginViewModel::class.java


    private fun View.enabled(value: Boolean) {
        if (value) {
            alpha = 1F
            isEnabled = true
        } else {
            alpha = 0.4F
            isEnabled = false
        }
    }

    override fun openRegister() {
        val registerIntent = Intent(this,RegisterActivity::class.java)
        startActivity(registerIntent)

    }

    override fun openGoogleIntent() {
        Log.d(TAG, "openGoogleIntent: ")

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent,RC_SIGN_IN)
    }

    override  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                Log.d(TAG, "onActivityResult: try")
                val account : GoogleSignInAccount = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "onActivityResult: fireBaseAuthWithGoogle ${account.id}")
                Log.d(TAG, "onActivityResult: ")

                viewModel.firebaseAuthWithGoogle(this,account.idToken!!)

            }catch (e: ApiException){
                Log.d(TAG, "onActivityResult: catch error ${e.localizedMessage}")
                Log.d(TAG, "onActivityResult: catch error $e")
            }
        }
    }

    override fun openHome() {
        Log.d(TAG, "openHome: ")
        val homeIntent = Intent(this,HomeActivity::class.java)
        startActivity(homeIntent)
        finish()
    }
    fun login(view: View) {
        viewModel.logIn(this)
    }
}

