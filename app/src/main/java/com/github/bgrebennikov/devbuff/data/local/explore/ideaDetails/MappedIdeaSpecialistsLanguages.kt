package com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails

import android.os.Parcelable
import com.github.bgrebennikov.devbuff.data.local.explore.MappedTechnologiesModel
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ListItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class MappedIdeaSpecialistsLanguages(
    val name: String
) : ListItem, Parcelable {
    override val itemId: Int
        get() = this.name.hashCode()
}