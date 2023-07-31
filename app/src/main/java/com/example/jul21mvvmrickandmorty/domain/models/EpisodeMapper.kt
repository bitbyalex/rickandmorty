package com.example.jul21mvvmrickandmorty.domain.models

import com.example.jul21mvvmrickandmorty.network.response.GetEpisodeByIdResponse

object EpisodeMapper {

    fun buildFrom(networkResponse : GetEpisodeByIdResponse) : Episode{
        return Episode(
            id = networkResponse.id,
            name = networkResponse.name,
            air_date = networkResponse.air_date,
            episode = networkResponse.episode
        )
    }
}