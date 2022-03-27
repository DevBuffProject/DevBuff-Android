package com.github.bgrebennikov.devbuff.presentation.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.github.bgrebennikov.devbuff.R
import com.github.bgrebennikov.devbuff.databinding.ActivityMainBinding
import com.github.bgrebennikov.devbuff.presentation.viewModels.AuthViewModel
import com.github.bgrebennikov.devbuff.presentation.viewModels.UserViewModel

class MainActivity : BaseActivity(){

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val userViewModel by lazy {
        viewModelFactory.create(UserViewModel::class.java)
    }


    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_container)
                as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.exploreFragment,
                R.id.myIdeasFragment,
                R.id.newIdeaFragment,
                R.id.settingsFragment
                )
        )

        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)


        setSupportActionBar(binding.mainToolbar)
        setupActionBarWithNavController(navController, appBarConfig)

        userViewModel.loadUser()



    }


    override fun onSupportNavigateUp(): Boolean {
        return navHostFragment.navController.navigateUp() || super.onSupportNavigateUp()
    }

}