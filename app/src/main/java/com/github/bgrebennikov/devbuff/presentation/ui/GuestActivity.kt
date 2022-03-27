package com.github.bgrebennikov.devbuff.presentation.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.bgrebennikov.devbuff.R
import com.github.bgrebennikov.devbuff.databinding.ActivityGuestBinding

class GuestActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityGuestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.gitLogin.setOnClickListener{
            githubAuth()
        }
    }

    private fun githubAuth(){
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(getString(R.string.api_endpoint_guthub_auth))
            )
        )
        finish()
    }

}