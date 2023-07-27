package com.example.jul21mvvmrickandmorty.characters

import com.example.jul21mvvmrickandmorty.network.NetworkLayer
import com.example.jul21mvvmrickandmorty.network.response.GetCharacterPageResponse

class CharacterRepository {

    suspend fun getCharacterList(pageIndex : Int)  : GetCharacterPageResponse? {
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if (request.failed || !request.isSuccessful){
            return null
        }

        return request.body
    }
}