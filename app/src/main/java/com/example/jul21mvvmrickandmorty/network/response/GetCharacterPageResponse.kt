package com.example.jul21mvvmrickandmorty.network.response

data class GetCharacterPageResponse(
    val info : PageInfo = PageInfo(),
    val results : List<GetCharacterByIdResponse> = emptyList()

)
