package com.example.chat.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.adapters.ChatsAdapter
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityHomeBinding
import com.example.chat.ui.login.LoginActivity
import java.util.*

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>(),Navigator {
    private  val TAG = "HomeActivity"
    lateinit var adapter :ChatsAdapter
    var newGroupDialog : AlertDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        val list = listOf<ChatModel>()
        adapter = ChatsAdapter(list)


        dataBinding.apply {

            vm = viewModel
            chatsRecyclerView.adapter = adapter

            viewModel.cancelClicked.observe(this@HomeActivity, Observer {
                Log.d(TAG, "onCreate: cancel clicked")
                newGroupDialog?.dismiss()
            })
        }
        viewModel.name.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
        })



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater :MenuInflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }



    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initializeViewModel(): HomeActivityViewModel {
        return ViewModelProvider(this).get(HomeActivityViewModel::class.java)
    }

    fun settings(item: MenuItem) {

    }
    fun newChat(item: MenuItem){
        Toast.makeText(this,"new Chat",Toast.LENGTH_LONG).show()
    }
    fun newGroup(item: MenuItem){
        newGroupDialog = AlertDialog.Builder(this).setView(layoutInflater.inflate(R.layout.new_group_dialog,null)).show()

    }
    fun logout(item: MenuItem){
        viewModel.logOut()
        openLogin()
    }

    override fun openLogin() {
        val homeIntent=Intent(this,LoginActivity::class.java)
        startActivity(homeIntent)
    }
}