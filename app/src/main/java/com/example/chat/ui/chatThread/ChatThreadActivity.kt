package com.example.chat.ui.chatThread

import android.content.SharedPreferences
import android.os.Bundle
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityChatThreadBinding
import com.example.chat.models.Message
import com.example.chat.onlineDatabase.models.Group
import com.example.chat.util.Constants
import com.stfalcon.chatkit.messages.MessagesListAdapter


class ChatThreadActivity : BaseActivity<ActivityChatThreadBinding, ChatThreadViewModel>(),Navigator {


    lateinit var adapter: MessagesListAdapter<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MessagesListAdapter<Message>(user.getString(Constants.USER_ID_KEY, Constants.NULL_VALUE), null)

        dataBinding.vm = viewModel
        viewModel.navigator =this
        val group = intent.getSerializableExtra(Constants.GROUP_EXTRA) as Group

        dataBinding.messagesList.setAdapter(adapter)
        observers()

        viewModel.listenForMessagesUpdate(group.id ?: "")

        dataBinding.input.setInputListener {
           viewModel.addMessage(it.toString(), group.id ?: "",this)
            return@setInputListener true
        }



    }

    override fun getLayoutId()=R.layout.activity_chat_thread

    override fun initializeViewModel()=ChatThreadViewModel::class.java
    fun observers(){
        viewModel.inputText.observe(this, androidx.lifecycle.Observer {
            adapter.addToStart(it, true)
        })
    }
}