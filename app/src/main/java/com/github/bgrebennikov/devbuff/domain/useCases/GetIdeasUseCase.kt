package com.github.bgrebennikov.devbuff.domain.useCases

import com.github.bgrebennikov.devbuff.data.local.explore.MappedIdeaModel
import com.github.bgrebennikov.devbuff.domain.Repository
import javax.inject.Inject

class GetIdeasUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(page : Int) : List<MappedIdeaModel>{
        return repository.getIdeas(1)
    }
}