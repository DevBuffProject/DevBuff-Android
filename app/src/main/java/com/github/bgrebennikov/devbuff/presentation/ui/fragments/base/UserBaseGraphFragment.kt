package com.github.bgrebennikov.devbuff.presentation.ui.fragments.base

import android.os.Bundle
import android.view.View
import androidx.navigation.ui.NavigationUI
import com.github.bgrebennikov.devbuff.databinding.FragmentUserBaseGraphBinding

class UserBaseGraphFragment : BaseFragment<FragmentUserBaseGraphBinding>(
    FragmentUserBaseGraphBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState == null){
            setupNavigation()
        }

    }

    private fun setupNavigation() {
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navHostMain.navController)
    }

}