package com.example.jul21mvvmrickandmorty.episodes

import com.example.jul21mvvmrickandmorty.domain.models.Episode

sealed class EpisodesUiModel {
    class Item(val episode: Episode) : EpisodesUiModel()
    class Header(val text : String) : EpisodesUiModel()
}