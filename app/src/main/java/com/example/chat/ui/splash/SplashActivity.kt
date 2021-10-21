package com.example.chat.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivitySplashBinding
import com.example.chat.ui.home.HomeActivity
import com.example.chat.ui.login.LoginActivity

class SplashActivity : BaseActivity<ActivitySplashBinding,SplashActivityViewModel>(),Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        viewModel.isUserLoggedIn(this)


    }

    override fun getLayoutId()=R.layout.activity_splash

    override fun initializeViewModel()=SplashActivityViewModel::class.java

    override fun openHome() {
        val intent =Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun openLogin() {
        val intent =Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}