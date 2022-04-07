package com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ideaDetails

import com.github.bgrebennikov.devbuff.presentation.ui.adapters.base.BaseDiffUtilItemCallback
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ListItem
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class IdeaSpecialistsAdapter : AsyncListDifferDelegationAdapter<ListItem>(
    BaseDiffUtilItemCallback()
) {

    init {
        delegatesManager
            .addDelegate(IdeaDetailsDelegate.specialistItemDelegate())
    }

}