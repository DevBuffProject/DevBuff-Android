package com.github.bgrebennikov.devbuff.domain.useCases

import com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails.MappedIdeaDetailsModel
import com.github.bgrebennikov.devbuff.data.remote.models.explore.ideaDetails.IdeaDetailsModel
import com.github.bgrebennikov.devbuff.domain.Repository
import javax.inject.Inject

class GetIdeaByIdUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(_id: String) : MappedIdeaDetailsModel {
        return repository.getIdeaById(_id)
    }

}