package com.example.jul21mvvmrickandmorty

import com.example.jul21mvvmrickandmorty.network.NetworkLayer
import com.example.jul21mvvmrickandmorty.network.response.GetCharacterByIdResponse

class SharedRepository {

    suspend fun getCharacterById(characterId : Int) : GetCharacterByIdResponse?{
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed){
            return null
        }
        if (!request.isSuccessful){
            return null
        }

        return request.body
    }
}