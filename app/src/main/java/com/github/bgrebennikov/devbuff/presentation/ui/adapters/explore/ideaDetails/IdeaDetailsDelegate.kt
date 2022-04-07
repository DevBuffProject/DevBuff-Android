package com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ideaDetails

import com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails.MappedIdeaFrameworks
import com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails.MappedIdeaSpecialists
import com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails.MappedIdeaSpecialistsLanguages
import com.github.bgrebennikov.devbuff.data.remote.models.explore.ideaDetails.IdeaDetailsSpecialists
import com.github.bgrebennikov.devbuff.databinding.IdeaChipItemBinding
import com.github.bgrebennikov.devbuff.databinding.IdeaSpecialistItemBinding
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ChipsAdapter
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ListItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object IdeaDetailsDelegate {

    fun specialistItemDelegate() =
        adapterDelegateViewBinding<MappedIdeaSpecialists, ListItem, IdeaSpecialistItemBinding>(
            { inflater, container ->
                IdeaSpecialistItemBinding.inflate(inflater, container, false)
            }
        ) {

            val languagesAdapter = ChipsAdapter()
            binding.languagesList.adapter = languagesAdapter

            val frameworksAdapter = ChipsAdapter()
            binding.technologiesList.adapter = frameworksAdapter


            bind {
                binding.specialist = item
                languagesAdapter.items = item.languages
                frameworksAdapter.items = item.frameworks
                binding.executePendingBindings()
            }

        }

    fun specialistsLanguages() =
        adapterDelegateViewBinding<MappedIdeaSpecialistsLanguages, ListItem, IdeaChipItemBinding>(
            { inflater, container ->
                IdeaChipItemBinding.inflate(inflater, container, false)
            }
        ){

            bind {
                with(binding){
                    title = item.name
                    executePendingBindings()
                }
            }

        }

    fun specialistFrameworks() =
        adapterDelegateViewBinding<MappedIdeaFrameworks, ListItem, IdeaChipItemBinding>(
            { inflater, container ->
                IdeaChipItemBinding.inflate(inflater, container, false)
            }
        ){

            bind {
                binding.title = item.name
                binding.executePendingBindings()
            }

        }


}