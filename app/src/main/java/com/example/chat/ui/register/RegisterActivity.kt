package com.example.chat.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityRegisterBinding
import com.example.chat.ui.login.Navigator

class RegisterActivity : BaseActivity<ActivityRegisterBinding,RegisterViewMdel>(),com.example.chat.ui.register.Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.vm = viewModel
        viewModel.navigator = this


    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initializeViewModel(): RegisterViewMdel {
        return ViewModelProvider(this).get(RegisterViewMdel::class.java)
    }


}