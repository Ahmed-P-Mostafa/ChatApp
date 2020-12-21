package com.example.chat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.R
import com.example.chat.databinding.ActivityHomeBinding
import com.example.chat.databinding.ChatItemBinding
import com.example.chat.ui.home.ChatModel

class ChatsAdapter(var list:List<ChatModel>?=null):RecyclerView.Adapter<ChatsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemRoomBinding = DataBindingUtil.inflate<ChatItemBinding>(LayoutInflater.from(parent.context), R.layout.chat_item,parent,false)
        return ViewHolder(itemRoomBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list?.get(position)!!)
    }

    override fun getItemCount(): Int = list!!.size

    fun changeData(list :List<ChatModel>){
        this.list = list
        notifyDataSetChanged()
    }
    class ViewHolder(val binding: ChatItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(chatModel: ChatModel){
            binding.chat = chatModel
            binding.executePendingBindings()
        }

    }
    /*class ChatAdapterDiffUtill:DiffUtil.ItemCallback<ChatModel>(){
        override fun areItemsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
            return newItem.equals(oldItem)
        }
    }*/
}