package com.github.bgrebennikov.devbuff.data.local.explore

import android.os.Parcelable
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ListItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class MappedIdeaRequirements(
    val name: String
): ListItem, Parcelable {
    override val itemId: Int
        get() = this.name.hashCode()
}