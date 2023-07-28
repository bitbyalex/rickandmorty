package com.example.jul21mvvmrickandmorty

import com.example.jul21mvvmrickandmorty.domain.models.Character
import com.example.jul21mvvmrickandmorty.domain.models.CharacterMapper
import com.example.jul21mvvmrickandmorty.network.NetworkLayer

class SharedRepository {

    suspend fun getCharacterById(characterId : Int) : Character?{
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed){
            return null
        }
        if (!request.isSuccessful){
            return null
        }

        return CharacterMapper.buildFrom(request.body)
    }
}