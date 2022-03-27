package com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore

import com.github.bgrebennikov.devbuff.presentation.ui.adapters.base.BaseDiffUtilItemCallback
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ExploreAdapterDelegate.headerItem
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ExploreAdapterDelegate.ideaPostDelegate
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ExploreAdapterDelegate.ideaPostProgressDefault
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.base.ExploreClickListener
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ExploreAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    var listener : ExploreClickListener? = null

    init {
        delegatesManager
            .addDelegate(ideaPostDelegate(){
                listener?.onClickIdeaItem(it)
            })
            .addDelegate(ideaPostProgressDefault())
            .addDelegate(headerItem())

    }

}