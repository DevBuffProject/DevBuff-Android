package com.github.bgrebennikov.devbuff.domain.useCases

import com.github.bgrebennikov.devbuff.data.local.SimpleIdeaModel
import com.github.bgrebennikov.devbuff.domain.Repository
import javax.inject.Inject

class GetIdeasUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(page : Int) : List<SimpleIdeaModel>{
        return repository.getIdeas(1)
    }
}