package com.example.chat.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.adapters.ChatsAdapter
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityHomeBinding
import com.example.chat.ui.chat.ChatActivity
import com.example.chat.ui.login.LoginActivity
import java.net.NoRouteToHostException

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>(),Navigator {
    var adapter = ChatsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        dataBinding.vm = viewModel
       
        dataBinding.chatsRecyclerView.adapter = adapter


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
        Toast.makeText(this,"settings",Toast.LENGTH_LONG).show()
    }
    fun newChat(item: MenuItem){
        openChat()
    }
    fun newGroup(item: MenuItem){
        Toast.makeText(this,"new group",Toast.LENGTH_LONG).show()
    }
    fun logout(item: MenuItem){
        viewModel.logOut()
        openLogin()
    }

    override fun openLogin() {
        val homeIntent=Intent(this,LoginActivity::class.java)
        startActivity(homeIntent)
    }

    override fun openChat() {
        val chatIntent = Intent(this,ChatActivity::class.java)
        startActivity(chatIntent)
    }
}