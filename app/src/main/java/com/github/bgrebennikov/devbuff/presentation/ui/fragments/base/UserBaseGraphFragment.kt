package com.github.bgrebennikov.devbuff.presentation.ui.fragments.base

import android.os.Bundle
import android.view.View
import androidx.navigation.navOptions
import androidx.navigation.ui.NavigationUI
import com.github.bgrebennikov.devbuff.common.extensions.findMainNavController
import com.github.bgrebennikov.devbuff.databinding.FragmentUserBaseGraphBinding

class UserBaseGraphFragment : BaseFragment<FragmentUserBaseGraphBinding>(
    FragmentUserBaseGraphBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNav = binding.bottomNavigationView

        bottomNav.setOnItemReselectedListener {

            navHostMain.navController.navigate(it.itemId, null, navOptions {
                popUpTo(it.itemId) {
                    inclusive = true
                }
            })
        }

        NavigationUI.setupWithNavController(bottomNav, navHostMain.navController)

    }

}