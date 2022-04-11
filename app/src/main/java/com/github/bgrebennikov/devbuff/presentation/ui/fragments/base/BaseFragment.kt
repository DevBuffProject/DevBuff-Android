package com.github.bgrebennikov.devbuff.presentation.ui.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBindingKtx
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.github.bgrebennikov.devbuff.BaseApplication
import com.github.bgrebennikov.devbuff.R
import javax.inject.Inject

open class BaseFragment<T : ViewBinding>(
    private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> T
) : Fragment() {

    val component by lazy {
        (requireActivity().application as BaseApplication).component
    }

    val navHostMain by lazy {
        childFragmentManager.findFragmentById(R.id.nav_container_main)
                as NavHostFragment
    }

//    val navHostRoot by lazy {
//        parentFragmentManager.findFragmentById(R.id.nav_container_root)
//    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _binding: T? = null
    val binding get() = _binding!!

    open fun onBind(binding: T) = Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateMethod.invoke(inflater, container, false)
        onBind(binding)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}