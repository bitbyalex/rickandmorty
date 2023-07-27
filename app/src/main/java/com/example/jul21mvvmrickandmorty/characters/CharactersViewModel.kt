package com.example.jul21mvvmrickandmorty.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.jul21mvvmrickandmorty.Constants
import com.example.jul21mvvmrickandmorty.network.response.GetCharacterByIdResponse

class CharactersViewModel  : ViewModel(){

    private val repository = CharacterRepository()
    private val pageListConfig : PagedList.Config = PagedList.Config.Builder()
        .setPageSize(Constants.PAGE_SIZE)
        .setPrefetchDistance(Constants.PREFETCH_DISTANCE)
        .build()

    private val dataSourceFactory = CharacterDataSourceFactory(viewModelScope,repository)
    val characterPagedListLiveData : LiveData<PagedList<GetCharacterByIdResponse>> =
        LivePagedListBuilder(dataSourceFactory, pageListConfig).build()
}