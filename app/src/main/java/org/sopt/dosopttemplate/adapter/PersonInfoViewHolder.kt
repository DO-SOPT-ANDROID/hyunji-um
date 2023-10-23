package org.sopt.dosopttemplate.adapter

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.databinding.ItemMeBinding
import org.sopt.dosopttemplate.model.PersonInfo

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: PersonInfo.FriendInfo) {
        binding.ivItemFriendImg.setImageResource(friendData.profileImage)
        binding.tvItemFriendName.text = friendData.name
        binding.tvItemFriendDescription.text = friendData.self_description
    }
}

class MyViewHolder(private val binding: ItemMeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(myData: PersonInfo.MyInfo) {
        binding.ivItemMeImg.setImageResource(myData.profileImage)
        binding.tvItemMeName.text = myData.name
        binding.tvItemMeDescription.text = myData.self_description
    }
}