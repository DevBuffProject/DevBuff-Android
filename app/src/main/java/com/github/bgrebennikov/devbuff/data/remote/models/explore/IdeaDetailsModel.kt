package com.github.bgrebennikov.devbuff.data.remote.models.explore

import com.google.gson.annotations.SerializedName

data class IdeaDetailsModel(
    @SerializedName("id")
    val id: String,

    @SerializedName("ownerIdea")
    val ownerIdea: OwnerIdea,

    @SerializedName("name")
    val name: String,

    @SerializedName("status")
    val status: IdeaStatus,

    @SerializedName("description")
    val description: String,

    @SerializedName("text")
    val body : String
    )