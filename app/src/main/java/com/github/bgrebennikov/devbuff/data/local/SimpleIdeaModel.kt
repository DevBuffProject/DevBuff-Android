package com.github.bgrebennikov.devbuff.data.local

import android.os.Parcelable
import com.github.bgrebennikov.devbuff.data.remote.models.explore.OwnerIdea
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ListItem
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class SimpleIdeaModel(
    val id: String,
    val ownerIdea: OwnerIdea,
    val name: String,
    val description: String,

    val requirements: List<SimpleIdeaRequirements>,
    val technologies: List<SimpleTechnologiesModel>,
    val languages: List<SimpleLanguagesModel>,

    val updateDate: String,
    val publishDate: String

) : ListItem, Parcelable {
    override val itemId: Int
        get() = this.id.hashCode()


}