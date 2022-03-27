package com.github.bgrebennikov.devbuff

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.github.bgrebennikov.devbuff.di.DaggerAppComponent
import com.github.bgrebennikov.devbuff.presentation.viewModels.AuthViewModel
import javax.inject.Inject

class BaseApplication : Application() {

    val component by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .dataStorage(DATA_STORAGE_NAME)
            .build()
    }


    private val authViewModel by lazy {
        viewModelFactory.create(AuthViewModel::class.java)
    }


    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    companion object{
        private const val DATA_STORAGE_NAME = "devbuff_ds"
    }

}