package com.github.bgrebennikov.devbuff.domain.user

import com.github.bgrebennikov.devbuff.data.remote.UserApiService
import com.github.bgrebennikov.devbuff.data.remote.models.user.UserProfileModel
import javax.inject.Inject

class UserRemoteRepositoryImpl @Inject constructor(
    private val userApi: UserApiService
) : UserRemoteRepository {

    override suspend fun getProfile(): UserProfileModel {
        return userApi.getProfile()
    }

    override suspend fun updateToken(refreshToken: String): String {
        return userApi.refresh(refreshToken)
    }


}