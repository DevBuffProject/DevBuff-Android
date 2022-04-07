package com.github.bgrebennikov.devbuff.presentation.ui.fragments.base

import com.github.bgrebennikov.devbuff.data.local.explore.MappedIdeaModel

interface ExploreClickListener {

    fun onClickIdeaItem(idea: MappedIdeaModel)

}