package com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore

import com.github.bgrebennikov.devbuff.presentation.ui.adapters.base.BaseDiffUtilItemCallback
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ideaDetails.IdeaDetailsDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ChipsAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager
            .addDelegate(ExploreAdapterDelegate.ideaChipItem())
            .addDelegate(ExploreAdapterDelegate.ideaChipLanguagesItem())
            .addDelegate(ExploreAdapterDelegate.ideaChipTechnologiesItem())

            .addDelegate(IdeaDetailsDelegate.specialistsLanguages())
            .addDelegate(IdeaDetailsDelegate.specialistFrameworks())

    }
}
