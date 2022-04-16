package com.github.bgrebennikov.devbuff.presentation.ui.fragments

import android.app.Dialog
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.github.bgrebennikov.devbuff.R
import com.github.bgrebennikov.devbuff.databinding.FragmentApplyIdeaBinding
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ideaDetails.IdeaSpecialistsAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ApplyIdeaFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentApplyIdeaBinding? = null
    val binding get() = _binding!!

    private val args : ApplyIdeaFragmentArgs by navArgs()

    private val adapterSpecialists = IdeaSpecialistsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApplyIdeaBinding.inflate(inflater, container, false)

        with(binding.ideaSpecialistsRecycler) {
            adapter = adapterSpecialists
        }


        adapterSpecialists.items = args.specialists.toList()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.skipCollapsed = false
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}