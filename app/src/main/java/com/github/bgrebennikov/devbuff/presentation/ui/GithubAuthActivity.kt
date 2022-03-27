package com.github.bgrebennikov.devbuff.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.github.bgrebennikov.devbuff.BaseApplication
import com.github.bgrebennikov.devbuff.R
import com.github.bgrebennikov.devbuff.common.TAG
import com.github.bgrebennikov.devbuff.databinding.ActivityGithubAuthBinding
import com.github.bgrebennikov.devbuff.databinding.ActivityGuestBinding
import com.github.bgrebennikov.devbuff.presentation.viewModels.AuthViewModel
import javax.inject.Inject

class GithubAuthActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val binding by lazy {
        ActivityGithubAuthBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as BaseApplication).component
    }

    private val authViewModel by lazy {
        viewModelFactory.create(AuthViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        authViewModel.isAuthenticated.observe(this){ isAuthenticated ->
            Log.i(TAG, "isAuthenticated: $isAuthenticated")
            if(isAuthenticated){
                startActivity(
                    Intent(
                        this, MainActivity::class.java
                    )
                )
                finish()
                return@observe
            }
        }

    }

    override fun onResume() {
        super.onResume()
        val authData = intent.data
        if (authData != null && authData.toString().startsWith(getString(R.string.redirect_uri))){
            val authCode = authData.getQueryParameter("code")
            if(authCode != null) authViewModel.authUser(authCode)
        }

    }
}