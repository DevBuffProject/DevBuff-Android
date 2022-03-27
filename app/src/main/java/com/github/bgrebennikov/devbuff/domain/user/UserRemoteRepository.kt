package com.github.bgrebennikov.devbuff.domain.user

import com.github.bgrebennikov.devbuff.data.remote.models.user.UserProfileModel

interface UserRemoteRepository {

    suspend fun getProfile() : UserProfileModel

    suspend fun updateToken(refreshToken : String) : String

}