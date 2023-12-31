package com.example.jul21mvvmrickandmorty.network

import com.example.jul21mvvmrickandmorty.network.response.GetCharacterByIdResponse
import com.example.jul21mvvmrickandmorty.network.response.GetCharacterPageResponse
import com.example.jul21mvvmrickandmorty.network.response.GetEpisodeByIdResponse
import com.example.jul21mvvmrickandmorty.network.response.GetEpisodePageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") characterId : Int
    ) : Response<GetCharacterByIdResponse>

    @GET("character/")
    suspend fun getCharactersPage(
        @Query("page") pageIndex : Int
    ) : Response<GetCharacterPageResponse>

    @GET("episode/{episode-id}")
    suspend fun getEpisodeById(
        @Path("episode-id") episodeId : Int
    ) : Response<GetEpisodeByIdResponse>

    @GET("episode/")
    suspend fun getEpisodesPage(
        @Query("page") pageIndex : Int
    ) : Response<GetEpisodePageResponse>

    @GET("episode/{episode-range}")
    suspend fun getEpisodeRange(
        @Path("episode-range") episodeRange : String
    ) : Response<List<GetEpisodeByIdResponse>>

}