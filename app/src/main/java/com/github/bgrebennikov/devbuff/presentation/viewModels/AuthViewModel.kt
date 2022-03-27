package com.github.bgrebennikov.devbuff.presentation.viewModels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.bgrebennikov.devbuff.common.TAG
import com.github.bgrebennikov.devbuff.data.remote.models.auth.AuthConfig
import com.github.bgrebennikov.devbuff.domain.useCases.AuthGithubUseCase
import com.github.bgrebennikov.devbuff.domain.useCases.StoreUserAuthUseCase
import com.github.bgrebennikov.devbuff.domain.useCases.UserIsAuthenticated
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthViewModel @Inject constructor(
    private val authGithubUseCase: AuthGithubUseCase,
    private val storeUserAuthUseCase: StoreUserAuthUseCase,
    private val userIsAuthenticated: UserIsAuthenticated,
    private val application: Application
) : ViewModel() {

    private val _isAuthenticated = MutableLiveData<Boolean>()
    val isAuthenticated: LiveData<Boolean> = _isAuthenticated

    init {
        viewModelScope.launch {
            checkIsAuthenticated()
        }
    }

    private suspend fun checkIsAuthenticated() {
        _isAuthenticated.postValue(
            userIsAuthenticated.invoke()
        )
    }

    fun authUser(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val authConfig: AuthConfig = authGithubUseCase.invoke(code)
                storeUserAuth(authConfig)
                _isAuthenticated.postValue(true)

                Toast.makeText(application.applicationContext, "Авторизовались!", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                Log.i(TAG, e.message.toString())
                e.printStackTrace()
            }
        }
    }

    private suspend fun storeUserAuth(config: AuthConfig) {
        storeUserAuthUseCase.invoke(config)
    }

}