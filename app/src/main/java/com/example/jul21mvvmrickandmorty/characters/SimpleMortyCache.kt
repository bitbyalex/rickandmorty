package com.example.jul21mvvmrickandmorty.characters

import com.example.jul21mvvmrickandmorty.domain.models.Character

object SimpleMortyCache {

    val characterMap = mutableMapOf<Int,Character>()
}