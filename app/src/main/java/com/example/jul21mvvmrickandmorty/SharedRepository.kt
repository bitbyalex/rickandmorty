package com.example.jul21mvvmrickandmorty

class SharedRepository {

    suspend fun getCharacterById(characterId : Int) : GetCharacterByIdResponse?{
        val response = NetworkLayer.apiClient.getCharacterById(characterId)

        if (response.isSuccessful){
            return response.body()!!
        }
        return null
    }
}