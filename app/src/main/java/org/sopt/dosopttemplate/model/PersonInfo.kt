package org.sopt.dosopttemplate.model

import androidx.annotation.DrawableRes

sealed class PersonInfo {
    data class MyInfo(
        @DrawableRes val profileImage: Int,
        val name: String,
        val self_description: String,
    ) : PersonInfo()

    data class FriendInfo(
        @DrawableRes val profileImage: Int,
        val name: String,
        val self_description: String,
    ) : PersonInfo()
}