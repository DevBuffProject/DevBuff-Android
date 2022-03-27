package com.github.bgrebennikov.devbuff.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.bgrebennikov.devbuff.di.keys.ViewModelKey
import com.github.bgrebennikov.devbuff.presentation.viewModels.AuthViewModel
import com.github.bgrebennikov.devbuff.presentation.viewModels.ExploreViewModel
import com.github.bgrebennikov.devbuff.presentation.viewModels.MainViewModelFactory
import com.github.bgrebennikov.devbuff.presentation.viewModels.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelsModule {


    @Binds
    fun bindViewModelFactory(impl: MainViewModelFactory) : ViewModelProvider.Factory

    @IntoMap
    @ViewModelKey(ExploreViewModel::class)
    @Binds
    fun bindSharedViewModel(viewModel: ExploreViewModel) : ViewModel

    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    @Binds
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @IntoMap
    @ViewModelKey(UserViewModel::class)
    @Binds
    fun bindUserViewModel(viewModel: UserViewModel): ViewModel

}