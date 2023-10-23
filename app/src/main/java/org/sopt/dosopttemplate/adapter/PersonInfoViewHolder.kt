package org.sopt.dosopttemplate.adapter

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.model.PersonInfo
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.databinding.ItemMeBinding

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: PersonInfo.FriendInfo) {
        binding.itemImage.setImageResource(friendData.profileImage)
        binding.itemName.text= friendData.name
        binding.itemDescription.text= friendData.self_description
    }
}

class MyViewHolder(private val binding: ItemMeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(myData: PersonInfo.MyInfo) {
        binding.itemImage.setImageResource(myData.profileImage)
        binding.itemName.text= myData.name
        binding.itemDescription.text= myData.self_description
    }
}