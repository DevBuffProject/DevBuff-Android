package com.github.bgrebennikov.devbuff.data.remote.models.explore

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OwnerIdea(

    @SerializedName("id")
    val id: String,

    @SerializedName("userName")
    val username: String,

    @SerializedName("firstName")
    val firstName: String?,

    @SerializedName("lastName")
    val lastName: String?

) : Parcelable
