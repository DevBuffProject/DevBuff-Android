package com.github.bgrebennikov.devbuff.presentation.viewModels

import android.util.Log
import androidx.lifecycle.*
import com.github.bgrebennikov.devbuff.common.TAG
import com.github.bgrebennikov.devbuff.data.ApiResponse
import com.github.bgrebennikov.devbuff.data.dataStore.DataStoreHelper
import com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails.MappedIdeaDetailsModel
import com.github.bgrebennikov.devbuff.data.remote.models.explore.ideaDetails.IdeaDetailsModel
import com.github.bgrebennikov.devbuff.domain.useCases.GetIdeaByIdUseCase
import com.github.bgrebennikov.devbuff.domain.useCases.GetIdeasUseCase
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.HeaderItemModel
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.IdeaPostProgress
import com.github.bgrebennikov.devbuff.presentation.ui.adapters.explore.ListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExploreViewModel @Inject constructor(
    private val getIdeasUseCase: GetIdeasUseCase,
    private val getIdeaByIdUseCase: GetIdeaByIdUseCase,
    private val dsHelper: DataStoreHelper
) : ViewModel() {

    private val _ideas = MutableLiveData<ApiResponse<List<ListItem>>>()
    val ideas : LiveData<ApiResponse<List<ListItem>>> get() = _ideas


    private val _singleIdea = MutableLiveData<ApiResponse<MappedIdeaDetailsModel>>()
    val singleIdea : LiveData<ApiResponse<MappedIdeaDetailsModel>> get() = _singleIdea

    init {

        loadIdeas(1)
//        dsHelper.setAccessToken("e4e39c7b-4e15-4ebf-b66b-3c802c92b224")

    }

    private fun loadIdeas(page : Int){
        viewModelScope.launch(Dispatchers.IO) {
            _ideas.postValue(ApiResponse.loading(
                data = IntRange(0, 3).map {
                    IdeaPostProgress
                }
            ))

            try{

                val response = getIdeasUseCase.invoke(page)

                val items = mutableListOf<ListItem>(
                    HeaderItemModel("Explore"),
                )
                items.addAll(response)
                _ideas.postValue(
                    ApiResponse.success(items)
                )
            } catch (e : Exception){
                e.printStackTrace()
                _ideas.postValue(ApiResponse.error(
                    message = e.message ?: "Error loading ideas",
                    data = null
                ))
            }

        }
    }

    fun loadSingleIdea(_id: String){

        Log.i(TAG, "loadSingleIdea: $this")

        viewModelScope.launch(Dispatchers.IO) {
            _singleIdea.postValue(
                ApiResponse.loading(null)
            )

            try {
                _singleIdea.postValue(
                    ApiResponse.success(
                        getIdeaByIdUseCase.invoke(_id)
                    )
                )
            } catch (e : Exception){
                _singleIdea.postValue(
                    ApiResponse.error(
                        data = null,
                        message = e.localizedMessage ?: "Error"
                    )
                )
                e.printStackTrace()
            }

        }
    }


    fun test(){
        Log.i(TAG, "test")
    }


}