package com.example.chat.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityChatBinding

class ChatActivity : BaseActivity<ActivityChatBinding, ChatViewModel>(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.vm = viewModel
        viewModel.navigator = this
        changeDrawable()

        //TODO complete chat model
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_chat
    }

    override fun initializeViewModel(): ChatViewModel {
       return ViewModelProvider(this).get(ChatViewModel::class.java)
    }
    fun changeDrawable(){
        viewModel.input.observe(this, Observer {
            if (!it.isNullOrEmpty()){
                dataBinding.recordButton.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_send_24))
            }else{
                dataBinding.recordButton.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_mic_24))
            }
        })
    }
    fun ImageView.setImageUrl(url:String){
        Glide.with(this@ChatActivity).load(url).dontAnimate().into(this)


    }
}