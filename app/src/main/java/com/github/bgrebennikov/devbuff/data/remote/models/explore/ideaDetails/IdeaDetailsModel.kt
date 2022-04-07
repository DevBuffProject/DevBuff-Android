package com.github.bgrebennikov.devbuff.data.remote.models.explore.ideaDetails

import com.github.bgrebennikov.devbuff.data.remote.models.explore.IdeaStatus
import com.github.bgrebennikov.devbuff.data.remote.models.explore.OwnerIdea
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
    val body : String,

    @SerializedName("specialist")
    val specialist : List<IdeaDetailsSpecialists>
)

