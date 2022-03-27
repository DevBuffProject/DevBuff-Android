package com.github.bgrebennikov.devbuff.domain.useCases

import com.github.bgrebennikov.devbuff.data.remote.models.user.UserProfileModel
import com.github.bgrebennikov.devbuff.domain.user.UserRemoteRepository
import javax.inject.Inject

class UserProfileUseCase @Inject constructor(
    private val repository: UserRemoteRepository
) {

    suspend operator fun invoke() : UserProfileModel{
        return repository.getProfile()
    }

}