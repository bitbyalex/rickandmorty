package com.example.jul21mvvmrickandmorty.episodes

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.example.jul21mvvmrickandmorty.R
import com.example.jul21mvvmrickandmorty.databinding.ModelEpisodeListItemBinding
import com.example.jul21mvvmrickandmorty.domain.models.Episode
import com.example.jul21mvvmrickandmorty.epoxy.ViewBindingKotlinModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@OptIn(ObsoleteCoroutinesApi::class)
class EpisodeListEpoxyController : PagingDataEpoxyController<Episode>() {

    override fun buildItemModel(currentPosition: Int, item: Episode?): EpoxyModel<*> {
        return EpisodeListItemEpoxyModel(
            episode = item!!,
            onClick = {episodeId ->
                // todo

            }
        ).id("episode_${item.id}")
    }

    data class EpisodeListItemEpoxyModel(
        val episode : Episode,
        val onClick : (Int) -> Unit
    ) : ViewBindingKotlinModel<ModelEpisodeListItemBinding>(R.layout.model_episode_list_item){
        override fun ModelEpisodeListItemBinding.bind() {
            episodeNumberTextView.text = episode.episode
            episodeNameTextView.text = episode.name
            episodeAirDateTextView.text = episode.air_date

            root.setOnClickListener{onClick(episode.id)}
        }
    }
}