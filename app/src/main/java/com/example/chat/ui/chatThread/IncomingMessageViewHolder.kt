package com.example.chat.ui.chatThread

import android.view.View
import android.widget.TextView
import com.example.chat.R
import com.example.chat.models.Message
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.android.synthetic.main.incoming_message_item.view.*

class IncomingMessageViewHolder(itemView: View?) :MessagesListAdapter.IncomingMessageViewHolder<Message>(itemView) {
    val name = itemView?.findViewById<TextView>(R.id.messageText)
    override fun onBind(data: Message?) {
        name?.setText(data?.user?.name)

    }
}