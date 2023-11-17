package org.sopt.dosopttemplate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.Dto.ResponseDto.ResponseFollowerDto
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FollowerAdapter(context: Context) : RecyclerView.Adapter<FollowerViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    // 임시의 빈 리스트
    private var friendList: List<ResponseFollowerDto.FollowerData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemFriendBinding.inflate(inflater, parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(friendList[position])
    }

    override fun getItemCount() = friendList.size

    // 임시 리스트에 준비해둔 가짜 리스트를 연결하는 함수
    fun setFriendList(friendList: List<ResponseFollowerDto.FollowerData>) {
        this.friendList = friendList.toList()
        notifyDataSetChanged()
    }
}