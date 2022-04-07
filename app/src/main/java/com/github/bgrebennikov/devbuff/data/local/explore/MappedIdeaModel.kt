package com.github.bgrebennikov.devbuff.data.local.explore

import android.os.Parcelable
import com.github.bgrebennikov.devbuff.data.remote.models.explore.OwnerIdea
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ListItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class MappedIdeaModel(
    val id: String,
    val ownerIdea: OwnerIdea,
    val name: String,
    val description: String,

    val requirements: List<MappedIdeaRequirements>,
    val technologies: List<MappedTechnologiesModel>,
    val languages: List<MappedLanguagesModel>,

    val updateDate: String,
    val publishDate: String

) : ListItem, Parcelable {
    override val itemId: Int
        get() = this.id.hashCode()


}