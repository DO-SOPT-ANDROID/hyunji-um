package org.sopt.dosopttemplate.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.Dto.ResponseDto.ResponseFollowerDto
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FollowerViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: ResponseFollowerDto.FollowerData) {
        binding.ivItemFriendImg.load(data.avatar)
        binding.tvItemFriendName.text = data.firstName
        binding.tvItemFriendDescription.text = data.email
    }
}