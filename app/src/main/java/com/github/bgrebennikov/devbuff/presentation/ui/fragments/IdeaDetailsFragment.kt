package com.github.bgrebennikov.devbuff.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.bgrebennikov.devbuff.common.TAG
import com.github.bgrebennikov.devbuff.common.extensions.findMainNavController
import com.github.bgrebennikov.devbuff.data.local.explore.Status
import com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails.MappedIdeaSpecialists
import com.github.bgrebennikov.devbuff.databinding.FragmentIdeaDetailsBinding
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ideaDetails.IdeaSpecialistsAdapter
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.base.BaseFragment
import com.github.bgrebennikov.devbuff.presentation.viewModels.ExploreViewModel

class IdeaDetailsFragment : BaseFragment<FragmentIdeaDetailsBinding>(
    FragmentIdeaDetailsBinding::inflate
) {

    private val args: IdeaDetailsFragmentArgs by navArgs()

    private val ideaInfo by lazy {
        args.ideaInfo
    }

    private val viewModel by lazy {
        viewModelFactory.create(ExploreViewModel::class.java)
    }

    private val adapterSpecialists = IdeaSpecialistsAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState == null) viewModel.loadSingleIdea(args.ideaInfo.id)

        binding.alreadyLoadedInfo = ideaInfo
        Log.i(TAG, "onViewCreated: $ideaInfo")

        viewModel.singleIdea.observe(viewLifecycleOwner) {
            it.let { apiResponse ->
                when (apiResponse.status) {

                    Status.LOADING -> {
                        binding.isLoading = true
                    }

                    Status.SUCCESS -> apiResponse.data?.let { idea ->
                        binding.isLoading = false
                        binding.ideaInfo = idea
                        handleJoinClick(idea.specialist)
                    }

                    Status.ERROR -> apiResponse.message?.let { error ->
                        Log.i(TAG, "onViewCreated: $error")
                        binding.isLoading = false
                    }

                }
            }
        }


        binding.appBar.onBackPressed {
            findNavController().popBackStack()
        }
    }


    private fun handleJoinClick(specialists: List<MappedIdeaSpecialists>) {
        with(binding.ideaJoinBtn) {
            setOnClickListener {
                findMainNavController().navigate(
                    IdeaDetailsFragmentDirections
                        .actionIdeaDetailsFragmentToApplyIdeaFragment(
                            specialists.toTypedArray()
                        )
                )
            }
        }
    }
}