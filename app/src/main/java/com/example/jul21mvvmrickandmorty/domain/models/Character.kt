package com.example.jul21mvvmrickandmorty.domain.models

data class Character(
    val episode: List<Episode>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
){
    data class Location(
        val name: String,
        val url: String
    )

    data class Origin(
        val name: String,
        val url: String
    )

    data class Episode(
        val id  : Int,
        val name : String,
        val air_date : String,
        val episode : String
    )
}
