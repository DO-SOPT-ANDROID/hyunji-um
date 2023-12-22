package org.sopt.dosopttemplate.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfo(
    val id: String = "",
    val pw: String = "",
    val nk: String = "",
    val hm: String = "",
) : Parcelable