package com.example.chat.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.adapters.ChatsAdapter
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityHomeBinding
import com.example.chat.onlineDatabase.group.Group
import com.example.chat.ui.chatThread.ChatThreadActivity
import com.example.chat.ui.login.LoginActivity
import com.example.chat.ui.newGroup.NewGroupActivity
import com.example.chat.util.ChatModel
import com.example.chat.util.Constants

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>(),Navigator {
    private  val TAG = "HomeActivity"
    private var adapter = ChatsAdapter(null)

    override fun onStart() {
        super.onStart()
        viewModel.getGroups()
        Log.d(TAG, "onStart: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        dataBinding.vm = viewModel
        viewModel.navigator = this
        dataBinding.chatsRecyclerView.adapter = adapter
        observers()
        adapter.onItemClickListener(object : ChatsAdapter.IOnItemClickListerner {
            override fun onItemClick(position: Int, group: Group) {
                super.onItemClick(position, group)
                openThread(group)

            }

        })



    }
    fun newGroup(item: MenuItem){
      val intent = Intent(this,NewGroupActivity::class.java)
        startActivity(intent)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater :MenuInflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }



    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initializeViewModel()=HomeActivityViewModel::class.java

    fun settings(item: MenuItem) {
        Toast.makeText(this,"Settings",Toast.LENGTH_LONG).show()

    }
    fun newChat(item: MenuItem){
        Toast.makeText(this,"new Chat",Toast.LENGTH_LONG).show()
    }

    fun logout(item: MenuItem){
        viewModel.logOut()
        openLogin()
    }

    override fun openLogin() {
        val homeIntent=Intent(this,LoginActivity::class.java)
        startActivity(homeIntent)
    }



    fun observers(){
        viewModel.list.observe(this, Observer {
            Log.d(TAG, "onCreate: list size : ${it.size}")
            adapter.changeData(it)
        })
    }

    override fun openThread(group:Group) {
        val intent = Intent(this,ChatThreadActivity::class.java)
        intent.putExtra(Constants.GROUP_EXTRA,group)
        startActivity(intent)
    }
}