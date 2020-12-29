package com.example.chat.ui.newGroup

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityNewGroupBinding

class NewGroupActivity : BaseActivity<ActivityNewGroupBinding, NewGroupViewModel>(), Navigator {
    private val TAG = "NewGroupActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.vm = viewModel
        viewModel.navigator =this
       observers()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_new_group
    }

    override fun initializeViewModel()=NewGroupViewModel::class.java

    override fun goBack() {
        finish()
    }
    fun observers(){
        viewModel.name.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
        })
        viewModel.groupNameLiveData.observe(this, Observer {
            Toast.makeText(this,"$it group created",Toast.LENGTH_SHORT).show()
        })

    }
}