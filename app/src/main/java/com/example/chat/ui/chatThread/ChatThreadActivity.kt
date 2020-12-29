package com.example.chat.ui.chatThread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityChatThreadBinding
import com.example.chat.util.Constants

class ChatThreadActivity : BaseActivity<ActivityChatThreadBinding,ChatThreadViewModel>(),Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.vm = viewModel
        viewModel.navigator =this
        val group = intent.getSerializableExtra(Constants.GROUP_EXTRA)

    }

    override fun getLayoutId()=R.layout.activity_chat_thread

    override fun initializeViewModel()=ChatThreadViewModel::class.java
}