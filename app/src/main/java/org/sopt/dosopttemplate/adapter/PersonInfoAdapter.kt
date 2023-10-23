package org.sopt.dosopttemplate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.databinding.ItemMeBinding
import org.sopt.dosopttemplate.model.PersonInfo

class PersonInfoAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private val my_info_type = 0
    private val friend_info_type = 1

    // 임시의 빈 리스트
    private var friendList: List<PersonInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            my_info_type -> {
                val binding = ItemMeBinding.inflate(inflater, parent, false)
                MyViewHolder(binding)
            }

            else -> {
                val binding = ItemFriendBinding.inflate(inflater, parent, false)
                FriendViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyViewHolder -> {
                val myInfo = friendList[position]
                holder.onBind(myInfo as PersonInfo.MyInfo)
            }

            is FriendViewHolder -> {
                val friendInfo = friendList[position]
                holder.onBind(friendInfo as PersonInfo.FriendInfo)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (friendList[position]) {
            is PersonInfo.MyInfo -> my_info_type
            is PersonInfo.FriendInfo -> friend_info_type
        }
    }

    override fun getItemCount() = friendList.size

    fun setFriendList(friendList: List<PersonInfo>) {
        this.friendList = friendList.toList()
        notifyDataSetChanged()
    }
}