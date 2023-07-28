package com.example.jul21mvvmrickandmorty.domain.models

import com.example.jul21mvvmrickandmorty.network.response.GetCharacterByIdResponse

object CharacterMapper {

    fun buildFrom(response : GetCharacterByIdResponse) : Character {
        return Character(
            episode = emptyList(),
            gender = response.gender,
            id = response.id,
            image = response.image,
            location = Character.Location(
                name = response.location.name,
                url = response.location.url
            ),
            name = response.name,
            origin = Character.Origin(
                name = response.origin.name,
                url = response.origin.url
            ),
            species = response.species,
            status = response.status
        )
    }
}