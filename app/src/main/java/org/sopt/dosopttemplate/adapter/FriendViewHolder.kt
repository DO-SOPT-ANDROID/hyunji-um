package org.sopt.dosopttemplate.adapter

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.model.Friend
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: Friend) {
        binding.itemImage.setImageResource(friendData.profileImage)
        binding.itemName.text= friendData.name
        binding.itemDescription.text= friendData.self_description
    }
}