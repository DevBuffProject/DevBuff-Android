package com.github.bgrebennikov.devbuff.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.bgrebennikov.devbuff.R
import com.github.bgrebennikov.devbuff.common.STAGED_BASE_IMAGE_URL
import com.github.bgrebennikov.devbuff.common.TAG
import com.github.bgrebennikov.devbuff.data.local.Status
import com.github.bgrebennikov.devbuff.databinding.FragmentSettingsBinding
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.base.BaseFragment
import com.github.bgrebennikov.devbuff.presentation.viewModels.UserViewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private val userViewModel by lazy {
        viewModelFactory.create(UserViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.userData.observe(viewLifecycleOwner){
            it.let { apiResponse ->
                when(apiResponse.status){

                    Status.LOADING ->{

                    }

                    Status.SUCCESS -> apiResponse.data?.let { user ->
                        binding.profileData = user
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)

    }

}