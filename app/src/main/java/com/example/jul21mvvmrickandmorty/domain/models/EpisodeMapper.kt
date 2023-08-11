package com.example.jul21mvvmrickandmorty.domain.models

import com.example.jul21mvvmrickandmorty.network.response.GetEpisodeByIdResponse

object EpisodeMapper {

    fun buildFrom(networkResponse : GetEpisodeByIdResponse) : Episode{
        return Episode(
            id = networkResponse.id,
            name = networkResponse.name,
            air_date = networkResponse.air_date,
            season_number = getSeasonFromEpisodeString(episode = networkResponse.episode),
            episode_number = getEpisodeFromEpisodeString(episode = networkResponse.episode)
        )
    }

    private fun getSeasonFromEpisodeString(episode : String) : Int {
        val endIndex = episode.indexOfFirst { it.equals('e', true) }
        if(endIndex == -1){
            return 0
        }
        return episode.substring(1,endIndex).toIntOrNull() ?: 0
    }

    private fun getEpisodeFromEpisodeString(episode: String) : Int {
        val startIndex = episode.indexOfFirst { it.equals('e', true) }
        if(startIndex == -1){
            return 0
        }
        return episode.substring(startIndex+1).toIntOrNull() ?: 0
    }
}