package com.example.chat.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityHomeBinding
import java.net.NoRouteToHostException

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>(),Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        dataBinding.vm = viewModel


    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initializeViewModel(): HomeActivityViewModel {
        return ViewModelProvider(this).get(HomeActivityViewModel::class.java)
    }
}