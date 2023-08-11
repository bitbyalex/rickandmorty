package com.example.jul21mvvmrickandmorty.domain.models

data class Episode(
    val id  : Int,
    val name : String,
    val air_date : String,
    val season_number : Int,
    val episode_number : Int
) {
    fun getFormattedSeason() : String {
        return "Season $season_number Episode $episode_number"
    }

    fun getFormattedSeasonTruncated() : String {
        return "S.$season_number E.$episode_number"
    }
}