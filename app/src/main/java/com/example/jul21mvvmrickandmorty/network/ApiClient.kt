package com.example.jul21mvvmrickandmorty.network

import com.example.jul21mvvmrickandmorty.network.response.GetCharacterByIdResponse
import com.example.jul21mvvmrickandmorty.network.response.GetCharacterPageResponse
import com.example.jul21mvvmrickandmorty.network.response.GetEpisodeByIdResponse
import com.example.jul21mvvmrickandmorty.network.response.GetEpisodePageResponse
import retrofit2.Response
import java.lang.Exception

class ApiClient(
    private val rickAndMortyService : RickAndMortyService
) {
    suspend fun getCharacterById(characterId: Int) : SimpleResponse<GetCharacterByIdResponse> {
        return safeApiCall { rickAndMortyService.getCharacterById(characterId) }
    }

    suspend fun getCharactersPage(pageIndex : Int) : SimpleResponse<GetCharacterPageResponse> {
        return safeApiCall { rickAndMortyService.getCharactersPage(pageIndex) }
    }

    suspend fun getEpisodeById(episodeId : Int) : SimpleResponse<GetEpisodeByIdResponse> {
        return safeApiCall { rickAndMortyService.getEpisodeById(episodeId) }
    }

    suspend fun getEpisodeRange(episodeRange : String) : SimpleResponse<List<GetEpisodeByIdResponse>> {
        return safeApiCall { rickAndMortyService.getEpisodeRange(episodeRange) }
    }

    suspend fun getEpisodesPage(pageIndex: Int) : SimpleResponse<GetEpisodePageResponse>{
        return safeApiCall { rickAndMortyService.getEpisodesPage(pageIndex) }
    }

    private inline fun <T> safeApiCall(apiCall : () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception){
            SimpleResponse.failure(e)
        }
    }
}