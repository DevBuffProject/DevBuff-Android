package com.github.bgrebennikov.devbuff.presentation.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.bgrebennikov.devbuff.common.TAG
import com.github.bgrebennikov.devbuff.data.ApiResponse
import com.github.bgrebennikov.devbuff.data.remote.models.user.UserProfileModel
import com.github.bgrebennikov.devbuff.domain.useCases.UserProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserViewModel @Inject constructor(
    private val profileUseCase: UserProfileUseCase
) : ViewModel() {

    private val _userData = MutableLiveData<ApiResponse<UserProfileModel>>()
    val userData : LiveData<ApiResponse<UserProfileModel>> = _userData

    init {
        Log.i(TAG, "UserViewModel: $this")
    }

    fun loadUser() {
        viewModelScope.launch(Dispatchers.IO) {

            try{
                _userData.postValue(
                    ApiResponse.success(
                        data = profileUseCase.invoke()
                    )
                )
            } catch (e : Exception){
                _userData.postValue(
                    ApiResponse.error(
                        message = e.localizedMessage ?: "Error loading profile",
                        data = null
                    )
                )
            }

        }
    }

//    fun loadUser() {
//        viewModelScope.launch {
//            _userData.postValue(
//                profileUseCase.invoke()
//            )
//        }
//    }


}