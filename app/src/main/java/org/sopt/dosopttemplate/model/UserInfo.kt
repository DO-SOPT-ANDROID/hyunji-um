package org.sopt.dosopttemplate.model

import java.io.Serializable

data class UserInfo(
    val id: String = "",
    val pw: String = "",
    val nk: String = "",
    val hm: String = "",
) : Serializable