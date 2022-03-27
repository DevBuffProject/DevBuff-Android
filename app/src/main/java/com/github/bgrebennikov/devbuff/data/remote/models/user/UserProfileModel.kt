package com.github.bgrebennikov.devbuff.data.remote.models.user

import com.google.gson.annotations.SerializedName

data class UserProfileModel(
    @SerializedName("id")
    val id: String,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String?,

    @SerializedName("userName")
    val username: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("statusEmailConfirm")
    val statusEmailConfirm: Boolean,

    @SerializedName("bio")
    val bio: String,

    @SerializedName("socialNetworks")
    val socialNetworks: UserSocialNetworks,

    @SerializedName("skills")
    val skills: List<UserSkills>

    )
