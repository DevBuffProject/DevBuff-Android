package com.github.bgrebennikov.devbuff.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import com.github.bgrebennikov.devbuff.common.TAG
import com.github.bgrebennikov.devbuff.data.local.Status
import com.github.bgrebennikov.devbuff.databinding.FragmentIdeaDetailsBinding
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.base.BaseFragment
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.base.utils.setTextFromHtml
import com.github.bgrebennikov.devbuff.presentation.viewModels.ExploreViewModel

class IdeaDetailsFragment : BaseFragment<FragmentIdeaDetailsBinding>(
    FragmentIdeaDetailsBinding::inflate
) {

    private val args : IdeaDetailsFragmentArgs by navArgs()

    private val ideaInfo by lazy{
        args.ideaInfo
    }

    private val viewModel by lazy {
        viewModelFactory.create(ExploreViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.alreadyLoadedInfo = ideaInfo
        Log.i(TAG, "onViewCreated: $ideaInfo")

        viewModel.loadSingleIdea(ideaInfo.id).observe(viewLifecycleOwner){
            it.let { apiResponse ->
                when(apiResponse.status){

                    Status.LOADING -> {
                        binding.isLoading = true
                    }

                    Status.SUCCESS -> apiResponse.data?.let { idea ->
                        binding.ideaBody.setTextFromHtml(idea.body)
                        binding.isLoading = false
                        binding.ideaInfo = idea
                    }

                    Status.ERROR -> apiResponse.message?.let { error ->
                        Log.i(TAG, "onViewCreated: $error")
                        binding.isLoading = false
                    }

                }
            }
        }


    }

}