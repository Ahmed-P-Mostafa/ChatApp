package com.example.chat.ui.chatThread

import android.os.Bundle
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.base.MyApplication
import com.example.chat.databinding.ActivityChatThreadBinding
import com.example.chat.models.Message
import com.example.chat.onlineDatabase.models.Group
import com.example.chat.onlineDatabase.models.User
import com.example.chat.util.Constants
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.android.synthetic.main.activity_chat_thread.*
import java.util.*


class ChatThreadActivity : BaseActivity<ActivityChatThreadBinding, ChatThreadViewModel>(),Navigator {
    val user = getSharedPreferences(Constants.USER_SHARED_PREFERENCES_FILE_NAME, MODE_PRIVATE)

    val adapter: MessagesListAdapter<Message> =
        MessagesListAdapter<Message>(user.getString(Constants.USER_ID_KEY,Constants.NULL_VALUE), null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.vm = viewModel
        viewModel.navigator =this
        val group = intent.getSerializableExtra(Constants.GROUP_EXTRA) as Group

        dataBinding.messagesList.setAdapter(adapter)
        observers()

        viewModel.listenForMessagesUpdate(group.id?:"")

        dataBinding.input.setInputListener {
           viewModel.addMessage(it.toString(),group.id?:"")
            return@setInputListener true
        }



    }

    override fun getLayoutId()=R.layout.activity_chat_thread

    override fun initializeViewModel()=ChatThreadViewModel::class.java
    fun observers(){
        viewModel.inputText.observe(this, androidx.lifecycle.Observer {
          adapter.addToStart(it,true)
        })
    }
}