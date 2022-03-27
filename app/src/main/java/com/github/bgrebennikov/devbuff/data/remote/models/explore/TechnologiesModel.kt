package com.github.bgrebennikov.devbuff.data.remote.models.explore

import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ListItem
import com.google.gson.annotations.SerializedName

data class TechnologiesModel(
    @SerializedName("name")
    val name: String,
) : ListItem{
    override val itemId: Int
        get() = this.name.hashCode()
}