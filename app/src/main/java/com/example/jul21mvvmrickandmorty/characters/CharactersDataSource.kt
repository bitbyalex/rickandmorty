package com.example.jul21mvvmrickandmorty.characters

import androidx.paging.PageKeyedDataSource
import com.example.jul21mvvmrickandmorty.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CharactersDataSource(
    private val coroutineScope : CoroutineScope,
    private val repository : CharacterRepository
) : PageKeyedDataSource<Int,GetCharacterByIdResponse>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GetCharacterByIdResponse>
    ) {
        coroutineScope.launch {
            val page = repository.getCharacterList(1)

            if(page == null){
                callback.onResult(emptyList(),null,null)
                return@launch
            }

            callback.onResult(page.results, null, getPageIndexFromNext(page.info.next))
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GetCharacterByIdResponse>
    ) {
        coroutineScope.launch {
            val page = repository.getCharacterList(params.key)

            if(page == null){
                callback.onResult(emptyList(),null)
                return@launch
            }

            callback.onResult(page.results, params.key +1)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GetCharacterByIdResponse>
    ) {
        // do nothing
    }

    private fun getPageIndexFromNext(next : String?) : Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }

}