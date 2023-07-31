package com.example.jul21mvvmrickandmorty

import android.util.Log
import com.example.jul21mvvmrickandmorty.domain.models.Character
import com.example.jul21mvvmrickandmorty.domain.models.CharacterMapper
import com.example.jul21mvvmrickandmorty.network.NetworkLayer
import com.example.jul21mvvmrickandmorty.network.response.GetCharacterByIdResponse
import com.example.jul21mvvmrickandmorty.network.response.GetEpisodeByIdResponse

class SharedRepository {

    suspend fun getCharacterById(characterId: Int): Character? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful) {
            return null
        }
        val networkEpisodes = getEpisodesFromCharacterResponse(request.body)

        return CharacterMapper.buildFrom(
            response = request.body,
            episodes = networkEpisodes
        )
    }

    private suspend fun getEpisodesFromCharacterResponse(
        characterResponse: GetCharacterByIdResponse
    ): List<GetEpisodeByIdResponse> {
        val episodeRange = characterResponse.episode.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1)
        }.joinToString(",")
        val request = NetworkLayer.apiClient.getEpisodeRange(episodeRange)
        Log.i("SharedRepository","request: $request")
        if (request.failed || !request.isSuccessful) {
            return emptyList()
        }

        return request.body
    }
}