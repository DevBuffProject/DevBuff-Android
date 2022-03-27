package com.github.bgrebennikov.devbuff.domain.useCases

import com.github.bgrebennikov.devbuff.data.remote.models.auth.AuthConfig
import com.github.bgrebennikov.devbuff.domain.Repository
import javax.inject.Inject

class AuthGithubUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(code: String) : AuthConfig{
        return repository.authGithubUser(code)
    }

}