package com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.bgrebennikov.devbuff.data.local.explore.MappedIdeaModel
import com.github.bgrebennikov.devbuff.data.local.explore.MappedIdeaRequirements
import com.github.bgrebennikov.devbuff.data.local.explore.MappedLanguagesModel
import com.github.bgrebennikov.devbuff.data.local.explore.MappedTechnologiesModel
import com.github.bgrebennikov.devbuff.databinding.HeaderTitleItemBinding
import com.github.bgrebennikov.devbuff.databinding.IdeaChipItemBinding
import com.github.bgrebennikov.devbuff.databinding.ItemIdeaBinding
import com.github.bgrebennikov.devbuff.databinding.ItemIdeaProgressBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object ExploreAdapterDelegate {


    fun ideaPostDelegate(onClickListener: (MappedIdeaModel) -> Unit) =
        adapterDelegateViewBinding<MappedIdeaModel, ListItem, ItemIdeaBinding>(
            { inflater, container ->
                ItemIdeaBinding.inflate(inflater, container, false)
            }
        ) {

            val sharedPool = RecyclerView.RecycledViewPool()
            sharedPool.setMaxRecycledViews(itemViewType, 1)

            val specialistsItems = ChipsAdapter()
            val technologiesItems = ChipsAdapter()
            val languagesItems = ChipsAdapter()

            with(binding){
                specialistsChipRecycler.setRecycledViewPool(sharedPool)
                specialistsChipRecycler.adapter = specialistsItems
                specialistsChipRecycler.setHasFixedSize(true)
                specialistsChipRecycler.itemAnimator = null
                specialistsChipRecycler.setItemViewCacheSize(1024*30)

                technologiesChipRecycler.setRecycledViewPool(sharedPool)
                technologiesChipRecycler.adapter = technologiesItems
                technologiesChipRecycler.setHasFixedSize(true)
                technologiesChipRecycler.itemAnimator = null
                technologiesChipRecycler.setItemViewCacheSize(1024*30)

                languagesChipRecycler.setRecycledViewPool(sharedPool)
                languagesChipRecycler.adapter = languagesItems
                languagesChipRecycler.setHasFixedSize(true)
                languagesChipRecycler.itemAnimator = null
                languagesChipRecycler.setItemViewCacheSize(1024*30)

            }



            bind {

                binding.idea = item

                specialistsItems.items = item.requirements
                technologiesItems.items = item.technologies
                languagesItems.items = item.languages

                binding.viewMoreBtn.setOnClickListener {
                    onClickListener.invoke(item)
                }

                binding.executePendingBindings()


            }

            onViewRecycled {
                if((binding.root.context as? Activity)?.isDestroyed?.not() == true){
                    Glide.with(binding.root)
                        .clear(binding.ownerPhoto)
                }
            }
        }

    fun headerItem() = adapterDelegateViewBinding<HeaderItemModel, ListItem, HeaderTitleItemBinding>(
        { inflater, container ->
            HeaderTitleItemBinding.inflate(inflater, container, false)
        }
    ){
        bind {
            binding.title = item.title
        }
    }

    fun ideaChipItem() = adapterDelegateViewBinding<MappedIdeaRequirements, ListItem, IdeaChipItemBinding>(
        { inflater, container ->
            IdeaChipItemBinding.inflate(inflater, container, false)
        }
    ){

        bind {
            binding.title = item.name
            binding.executePendingBindings()
        }
    }

    fun ideaChipLanguagesItem() = adapterDelegateViewBinding<MappedLanguagesModel, ListItem, IdeaChipItemBinding>(
        { inflater, container ->
            IdeaChipItemBinding.inflate(inflater, container, false)
        }
    ){
        bind {
            binding.title = item.name
            binding.executePendingBindings()
        }
    }

    fun ideaChipTechnologiesItem() = adapterDelegateViewBinding<MappedTechnologiesModel, ListItem, IdeaChipItemBinding>(
        { inflater, container ->
            IdeaChipItemBinding.inflate(inflater, container, false)
        }
    ){
        bind {
            binding.title = item.name
            binding.executePendingBindings()
        }
    }

    fun ideaPostProgressDefault() =
        adapterDelegateViewBinding<IdeaPostProgress, ListItem, ItemIdeaProgressBinding>(
            { inflater, container ->
                ItemIdeaProgressBinding.inflate(inflater, container, false)
            }
        ) {

        }

}