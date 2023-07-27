package com.example.jul21mvvmrickandmorty.network

import com.example.jul21mvvmrickandmorty.network.response.GetCharacterByIdResponse
import com.example.jul21mvvmrickandmorty.network.response.GetCharacterPageResponse
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

    private inline fun <T> safeApiCall(apiCall : () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception){
            SimpleResponse.failure(e)
        }
    }
}