package com.github.bgrebennikov.devbuff.data.remote.models.auth

import com.google.gson.annotations.SerializedName

data class AuthConfig(
    @SerializedName("access_token")
    val access_token : String,

    @SerializedName("token_type")
    val token_type : String,

    @SerializedName("refresh_token")
    val refresh_token : String,

    @SerializedName("expires_in")
    val expires_in : Long,

    @SerializedName("scope")
    val scope: String

)