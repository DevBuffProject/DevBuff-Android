package com.github.bgrebennikov.devbuff.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.bgrebennikov.devbuff.BaseApplication
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    val component by lazy {
        (application as BaseApplication).component
    }

}