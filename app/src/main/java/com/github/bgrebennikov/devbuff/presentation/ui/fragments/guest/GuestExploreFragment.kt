package com.github.bgrebennikov.devbuff.presentation.ui.fragments.guest

import android.content.Context
import android.widget.Toast
import com.github.bgrebennikov.devbuff.data.local.explore.MappedIdeaModel
import com.github.bgrebennikov.devbuff.data.local.explore.Status
import com.github.bgrebennikov.devbuff.databinding.FragmentGuestExploreBinding
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ExploreAdapter
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.base.BaseFragment
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.base.ExploreClickListener
import com.github.bgrebennikov.devbuff.presentation.viewModels.ExploreViewModel


class GuestExploreFragment : BaseFragment<FragmentGuestExploreBinding>(
    FragmentGuestExploreBinding::inflate
) {

    override fun onBind(binding: FragmentGuestExploreBinding) {
        super.onBind(binding)

        binding.ideasListRecycler.adapter = adapter
        binding.ideasListRecycler.setHasFixedSize(true)


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

        adapter.listener = object : ExploreClickListener {
            override fun onClickIdeaItem(idea: MappedIdeaModel) {
                Toast.makeText(context, "Guest: ${idea.name}", Toast.LENGTH_LONG).show()
            }

        }
    }

    private val viewModel by lazy {
        viewModelFactory.create(ExploreViewModel::class.java)
    }

    private val adapter = ExploreAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }



}