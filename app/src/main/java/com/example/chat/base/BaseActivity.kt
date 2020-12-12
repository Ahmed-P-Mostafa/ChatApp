package com.example.chat.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth

abstract class BaseActivity<DB:ViewDataBinding,VM:ViewModel> :AppCompatActivity() {

    lateinit var dataBinding: DB
    lateinit var viewModel:VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this,getLayoutId())
        viewModel = initializeViewModel()



    }
    abstract fun getLayoutId():Int
    abstract fun initializeViewModel():VM
}