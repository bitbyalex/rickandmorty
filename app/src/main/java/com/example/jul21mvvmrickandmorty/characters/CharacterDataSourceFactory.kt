package com.example.jul21mvvmrickandmorty.characters

import androidx.paging.DataSource
import com.example.jul21mvvmrickandmorty.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.CoroutineScope

class CharacterDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val characterRepository: CharacterRepository
) : DataSource.Factory<Int,GetCharacterByIdResponse>() {
    override fun create(): DataSource<Int, GetCharacterByIdResponse> {

        return CharactersDataSource(coroutineScope,characterRepository)
    }
}