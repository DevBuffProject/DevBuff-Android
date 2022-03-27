package com.github.bgrebennikov.devbuff.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.github.bgrebennikov.devbuff.presentation.viewModels.AuthViewModel

@SuppressLint("CustomSplashScreen")
class SplashScreen : BaseActivity() {

    private val authViewModel by lazy {
        viewModelFactory.create(AuthViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        authViewModel.isAuthenticated.observe(this){ isAuthenticated ->
            val intent = if(isAuthenticated) Intent(this, MainActivity::class.java)
            else Intent(this, GuestActivity::class.java)

            startActivity(intent)
            finish()
            return@observe
        }


    }

}