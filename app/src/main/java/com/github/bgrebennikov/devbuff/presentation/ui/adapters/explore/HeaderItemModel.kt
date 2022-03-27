package com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore

data class HeaderItemModel(
    val title: String
) : ListItem{
    override val itemId: Int
        get() = this.title.hashCode()
}