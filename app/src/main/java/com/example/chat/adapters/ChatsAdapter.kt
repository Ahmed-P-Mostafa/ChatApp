package com.example.chat.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.R

import com.example.chat.databinding.GroupItemBinding
import com.example.chat.onlineDatabase.models.Group



class ChatsAdapter(var list:List<Group>?):RecyclerView.Adapter<ChatsAdapter.ViewHolder>() {
    private val TAG = "ChatsAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemRoomBinding:GroupItemBinding  = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.group_item,parent,false)
        return ViewHolder(itemRoomBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list?.get(position)
        holder.bind(list?.get(position))
        holder.itemView.setOnClickListener {
            if (item != null) {
                onGroupClickListener?.onItemClick(position,item)
            }
        }
    }

    override fun getItemCount(): Int{
        return list?.size?:0
    }

    fun changeData(list :List<Group>){
        this.list = list
        notifyDataSetChanged()
        Log.d(TAG, "changeData: new list size : ${list.size} ")
    }

    class ViewHolder(private val binding:GroupItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(group: Group?){
            Log.d("ChatsAdapter", "bind: ")
            binding.group = group
            binding.invalidateAll()

        }

    }
    interface IOnItemClickListerner{
        fun onItemClick(position:Int,group: Group){}
    }
    var onGroupClickListener : IOnItemClickListerner?=null

    fun onItemClickListener(listener:IOnItemClickListerner){
        this.onGroupClickListener = listener
    }


}