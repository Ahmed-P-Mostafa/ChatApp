package com.example.chat.ui.login

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityLoginBinding
import com.example.chat.ui.register.RegisterActivity
import kotlinx.coroutines.flow.collect

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(),Navigator {

    //val loginViewModel : LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        isUserLogedIn()
        dataBinding.vm = viewModel


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

    override fun initializeViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    fun isUserLogedIn(){

        viewModel.isSignedIn()
        // validate if the user is logged in or not to switch between login or home activity
        viewModel.isSignedLiveData.observe(this, Observer {
            if (it){
                Toast.makeText(this,"User Logged In",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"User Not Logged In",Toast.LENGTH_LONG).show()
            }
        })
    }

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


}