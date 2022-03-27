package com.github.bgrebennikov.devbuff.data.remote.models.auth

import com.google.gson.annotations.SerializedName

data class TokenModel(
    @SerializedName("access_token")
    val access_token : String
)
