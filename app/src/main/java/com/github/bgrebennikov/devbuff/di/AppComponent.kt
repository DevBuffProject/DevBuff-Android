package com.github.bgrebennikov.devbuff.di

import android.app.Application
import com.github.bgrebennikov.devbuff.di.modules.NetworkModule
import com.github.bgrebennikov.devbuff.di.modules.ApiRepositoryModule
import com.github.bgrebennikov.devbuff.di.modules.DataStoreModule
import com.github.bgrebennikov.devbuff.di.modules.ViewModelsModule
import com.github.bgrebennikov.devbuff.di.qualifiers.DataStorage
import com.github.bgrebennikov.devbuff.domain.user.UserRemoteRepository
import com.github.bgrebennikov.devbuff.domain.user.UserRemoteRepositoryImpl
import com.github.bgrebennikov.devbuff.presentation.ui.GithubAuthActivity
import com.github.bgrebennikov.devbuff.presentation.ui.MainActivity
import com.github.bgrebennikov.devbuff.presentation.ui.SplashScreen
import com.github.bgrebennikov.devbuff.presentation.ui.BaseActivity
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.guest.GuestExploreFragment
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.ExploreFragment
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.IdeaDetailsFragment
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.SettingsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ApiRepositoryModule::class,
        ViewModelsModule::class,
        DataStoreModule::class
    ]
)
interface AppComponent {

    fun inject(fragment: ExploreFragment)
    fun inject(fragment: GuestExploreFragment)
    fun inject(fragment: SettingsFragment)
    fun inject(fragment: IdeaDetailsFragment)

    fun inject(activity: MainActivity)
    fun inject(activity: GithubAuthActivity)
    fun inject(activity : SplashScreen)
    fun inject(application: Application)
    fun inject(activity: BaseActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun dataStorage(
            @DataStorage dataStorageName: String
        ): Builder



        fun build(): AppComponent

    }

}