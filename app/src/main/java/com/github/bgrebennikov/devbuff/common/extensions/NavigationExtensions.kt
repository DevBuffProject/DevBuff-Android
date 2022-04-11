package com.github.bgrebennikov.devbuff.common.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.github.bgrebennikov.devbuff.R

fun Fragment.findRootNavController() : NavController{

    val rootHost = requireActivity().supportFragmentManager
        .findFragmentById(R.id.nav_container_root) as NavHostFragment?

    return rootHost?.navController ?: findNavController()

}

fun Fragment.findMainNavController() : NavController{
    val mainHost = requireActivity().supportFragmentManager
        .findFragmentById(R.id.nav_container_main) as NavHostFragment?

    return mainHost?.navController ?: findNavController()
}