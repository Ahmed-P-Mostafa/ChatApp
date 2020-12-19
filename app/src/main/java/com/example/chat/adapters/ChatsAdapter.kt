package com.example.chat.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.databinding.ActivityHomeBinding

class ChatsAdapter():RecyclerView.Adapter<ChatsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    fun changeData(){}
    class ViewHolder(binding: ActivityHomeBinding):RecyclerView.ViewHolder(binding.root){

    }
}