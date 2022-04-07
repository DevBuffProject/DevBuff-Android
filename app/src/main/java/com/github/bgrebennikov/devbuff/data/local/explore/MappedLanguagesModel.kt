package com.github.bgrebennikov.devbuff.data.local.explore

import android.os.Parcelable
import com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails.MappedIdeaFrameworks
import com.github.bgrebennikov.devbuff.data.remote.models.explore.ideaDetails.IdeaDetailsFrameworks
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ListItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class MappedLanguagesModel(
    val name: String,
) : ListItem, Parcelable {
    override val itemId: Int
        get() = name.hashCode()
}