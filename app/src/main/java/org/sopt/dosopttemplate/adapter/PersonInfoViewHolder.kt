package org.sopt.dosopttemplate.adapter

import android.view.View
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
        if (friendData.music != null) {
            binding.btnItemFriendMusic.text = friendData.music
            binding.btnItemFriendMusic.visibility = View.VISIBLE
        }
        else {
            binding.btnItemFriendMusic.visibility = View.INVISIBLE
        }
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