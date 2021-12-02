package com.example.keepgoon

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.keepgoon.databinding.ItemUserBinding

class UserAdapter(val items: List<User>)
    : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    companion object{
        const val TAG: String = "LOG"
    }

    inner class UserViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        Log.d(TAG, "[UserAdapter] - onCreateViewHolder() 호출")

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)

        return UserViewHolder(ItemUserBinding.bind(view)) //item_user 모양을 하는 뷰 홀더를 생성
    }

    //holder안에 어떤 data를 세팅해줄건지
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.user = items[position]
    }

    override fun getItemCount(): Int = items.size
}