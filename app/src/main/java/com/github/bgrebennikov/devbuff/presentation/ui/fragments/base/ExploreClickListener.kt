package com.github.bgrebennikov.devbuff.presentation.ui.fragments.base

import com.github.bgrebennikov.devbuff.data.local.SimpleIdeaModel

interface ExploreClickListener {

    fun onClickIdeaItem(idea: SimpleIdeaModel)

}