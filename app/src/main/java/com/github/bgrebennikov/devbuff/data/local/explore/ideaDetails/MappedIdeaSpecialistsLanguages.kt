package com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails

import com.github.bgrebennikov.devbuff.data.local.explore.MappedTechnologiesModel
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ListItem

data class MappedIdeaSpecialistsLanguages(
    val name: String
) : ListItem{
    override val itemId: Int
        get() = this.name.hashCode()
}