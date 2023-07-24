package com.example.jul21mvvmrickandmorty

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {

    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") characterId : Int
    ) : Response<GetCharacterByIdResponse>
}