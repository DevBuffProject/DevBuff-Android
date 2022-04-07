package com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails

import com.github.bgrebennikov.devbuff.data.remote.models.explore.IdeaStatus
import com.github.bgrebennikov.devbuff.data.remote.models.explore.OwnerIdea
import com.github.bgrebennikov.devbuff.data.remote.models.explore.ideaDetails.IdeaDetailsSpecialists
import com.google.gson.annotations.SerializedName

data class MappedIdeaDetailsModel(
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
    val specialist : List<MappedIdeaSpecialists>
)