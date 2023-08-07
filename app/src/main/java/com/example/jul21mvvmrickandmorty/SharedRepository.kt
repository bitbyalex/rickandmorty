package com.example.jul21mvvmrickandmorty

import android.util.Log
import com.example.jul21mvvmrickandmorty.domain.models.Character
import com.example.jul21mvvmrickandmorty.domain.models.CharacterMapper
import com.example.jul21mvvmrickandmorty.network.NetworkLayer
import com.example.jul21mvvmrickandmorty.network.response.GetCharacterByIdResponse
import com.example.jul21mvvmrickandmorty.network.response.GetEpisodeByIdResponse

class SharedRepository {

    suspend fun getCharacterById(characterId: Int): Character? {
        val cacheCharacter = SimpleMortyCache.characterMap[characterId]

        if(cacheCharacter != null) {
            return cacheCharacter
        }
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful) {
            return null
        }
        val networkEpisodes = getEpisodesFromCharacterResponse(request.body)

        val character =  CharacterMapper.buildFrom(
            response = request.body,
            episodes = networkEpisodes
        )
        SimpleMortyCache.characterMap[characterId] = character
        return character
    }

    private suspend fun getEpisodesFromCharacterResponse(
        characterResponse: GetCharacterByIdResponse
    ): List<GetEpisodeByIdResponse> {
        val episodeRange = characterResponse.episode.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1)
        }.toString()
        val request = NetworkLayer.apiClient.getEpisodeRange(episodeRange)

        if (request.failed || !request.isSuccessful) {
            return emptyList()
        }

        return request.body
    }
}