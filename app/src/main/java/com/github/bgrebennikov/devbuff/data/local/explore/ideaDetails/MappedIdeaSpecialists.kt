package com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails

import com.github.bgrebennikov.devbuff.data.local.explore.MappedLanguagesModel
import com.github.bgrebennikov.devbuff.data.remote.models.explore.LanguagesModel
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ListItem

data class MappedIdeaSpecialists(
    val id: String,
    val count: Int,
    val name: String,
    val languages: List<MappedIdeaSpecialistsLanguages>,
    val frameworks: List<MappedIdeaFrameworks>
) : ListItem{
    override val itemId: Int
        get() = this.id.hashCode()
}
