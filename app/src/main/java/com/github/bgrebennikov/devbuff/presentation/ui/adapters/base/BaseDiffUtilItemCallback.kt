package com.github.bgrebennikov.devbuff.presentation.ui.adapters.base

import androidx.recyclerview.widget.DiffUtil
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ListItem

class BaseDiffUtilItemCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem::class == newItem::class && oldItem.itemId == newItem.itemId
    }

    override fun getChangePayload(oldItem: ListItem, newItem: ListItem): Any? {
        return super.getChangePayload(oldItem, newItem)
    }


}