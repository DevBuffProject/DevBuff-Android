package com.github.bgrebennikov.devbuff.data.remote.models.explore

import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ListItem
import com.google.gson.annotations.SerializedName
import java.util.*

data class IdeaModel(
    @SerializedName("id")
    val id: String,

    @SerializedName("ownerIdea")
    val ownerIdea: OwnerIdea,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("requirements")
    val requirements: List<RequirementsModel>,


    @SerializedName("updateDate")
    val updateDate: String,

    @SerializedName("publishDate")
    val publishDate: String

) : ListItem{
    override val itemId: Int
        get() = this.id.hashCode()
}