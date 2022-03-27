package com.github.bgrebennikov.devbuff.domain.useCases

import com.github.bgrebennikov.devbuff.data.remote.models.explore.IdeaDetailsModel
import com.github.bgrebennikov.devbuff.domain.Repository
import com.github.bgrebennikov.devbuff.domain.user.UserRemoteRepository
import javax.inject.Inject

class GetIdeaByIdUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(_id: String) : IdeaDetailsModel{
        return repository.getIdeaById(_id)
    }

}