package com.github.bgrebennikov.devbuff.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.bgrebennikov.devbuff.common.extensions.findMainNavController
import com.github.bgrebennikov.devbuff.data.local.explore.MappedIdeaModel
import com.github.bgrebennikov.devbuff.data.local.explore.Status
import com.github.bgrebennikov.devbuff.databinding.FragmentExploreBinding
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ExploreAdapter
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.base.BaseFragment
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.base.ExploreClickListener
import com.github.bgrebennikov.devbuff.presentation.viewModels.ExploreViewModel

class ExploreFragment : BaseFragment<FragmentExploreBinding>(FragmentExploreBinding::inflate) {

    private val viewModel by lazy {
        viewModelFactory.create(ExploreViewModel::class.java)
    }

    private val adapter = ExploreAdapter()

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPool = RecyclerView.RecycledViewPool()
        sharedPool.setMaxRecycledViews(0, 30)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        layoutManager.initialPrefetchItemCount = 3

        with(binding){
            ideasListRecycler.layoutManager = layoutManager
            ideasListRecycler.setRecycledViewPool(sharedPool)
            ideasListRecycler.adapter = adapter
            ideasListRecycler.setHasFixedSize(true)
            ideasListRecycler.setItemViewCacheSize(1024)
        }


        viewModel.ideas.observe(viewLifecycleOwner){
            it.let { apiResponse ->
                when(apiResponse.status){
                    Status.SUCCESS -> apiResponse.data?.let { ideas ->
                        adapter.items = ideas
                    }
                    Status.LOADING -> apiResponse.data?.let { placeholder ->
                        adapter.items = placeholder
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }


        adapter.listener = object : ExploreClickListener{
            override fun onClickIdeaItem(idea: MappedIdeaModel) {
                findMainNavController().navigate(
                    ExploreFragmentDirections.actionExploreFragmentToIdeaDetailsFragment(idea)
                )
            }

        }
    }


}