package com.github.bgrebennikov.devbuff.data.remote.models.explore

import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ListItem
import com.google.gson.annotations.SerializedName

data class RequirementsModel(

    @SerializedName("name")
    val name: String,

    @SerializedName("languages")
    val languages : List<LanguagesModel>

) : ListItem{
    override val itemId: Int
        get() = this.name.hashCode()
}
