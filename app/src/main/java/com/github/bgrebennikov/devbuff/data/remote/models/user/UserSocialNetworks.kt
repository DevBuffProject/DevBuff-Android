package com.github.bgrebennikov.devbuff.data.remote.models.user

import com.google.gson.annotations.SerializedName

data class UserSocialNetworks(
    @SerializedName("telegram")
    val telegram : String?,

    @SerializedName("vk")
    val vk : String?,

    @SerializedName("skype")
    val skype : String?,

    @SerializedName("discord")
    val discord : String?
)
