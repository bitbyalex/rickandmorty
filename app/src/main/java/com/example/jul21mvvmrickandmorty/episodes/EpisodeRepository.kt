package com.example.jul21mvvmrickandmorty.episodes

import com.example.jul21mvvmrickandmorty.network.NetworkLayer
import com.example.jul21mvvmrickandmorty.network.response.GetEpisodePageResponse

class EpisodeRepository {
    suspend fun fetchEpisodePage(pageIndex : Int) : GetEpisodePageResponse? {
        val request = NetworkLayer.apiClient.getEpisodesPage(pageIndex)

        if(!request.isSuccessful){
            return null
        }

        return request.body
    }
}