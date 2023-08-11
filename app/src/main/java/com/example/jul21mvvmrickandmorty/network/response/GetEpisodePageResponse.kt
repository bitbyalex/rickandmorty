package com.example.jul21mvvmrickandmorty.network.response

data class GetEpisodePageResponse(
    val info : PageInfo = PageInfo(),
    val results : List<GetEpisodeByIdResponse> = emptyList()
)
