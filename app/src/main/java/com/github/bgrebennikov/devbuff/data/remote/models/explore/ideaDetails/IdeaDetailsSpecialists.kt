package com.github.bgrebennikov.devbuff.data.remote.models.explore.ideaDetails

import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ListItem

data class IdeaDetailsSpecialists(
    val id: String,
    val count: Int,
    val name: String,
    val languages: List<IdeaDetailsLanguages>
) : ListItem{
    override val itemId: Int
        get() = this.id.hashCode()
}