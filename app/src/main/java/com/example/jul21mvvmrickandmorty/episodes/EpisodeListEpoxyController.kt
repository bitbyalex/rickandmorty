package com.example.jul21mvvmrickandmorty.episodes

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.example.jul21mvvmrickandmorty.R
import com.example.jul21mvvmrickandmorty.databinding.ModelEpisodeListItemBinding
import com.example.jul21mvvmrickandmorty.databinding.ModelEpisodeListTitleBinding
import com.example.jul21mvvmrickandmorty.domain.models.Episode
import com.example.jul21mvvmrickandmorty.epoxy.ViewBindingKotlinModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@OptIn(ObsoleteCoroutinesApi::class)
class EpisodeListEpoxyController : PagingDataEpoxyController<EpisodesUiModel>() {

    override fun buildItemModel(currentPosition: Int, item: EpisodesUiModel?): EpoxyModel<*> {
        return when (item!!){
            is EpisodesUiModel.Item -> {
                val episode = (item as EpisodesUiModel.Item).episode
                EpisodeListItemEpoxyModel(
                    episode = episode,
                    onClick = {episodeId ->
                        // todo

                    }
                ).id("episode_${episode.id}")
            }
            is EpisodesUiModel.Header -> {
                val headerText = (item as EpisodesUiModel.Header).text
                EpisodeListTitleEpoxyModel(
                    title = headerText
                ).id("header_$headerText")
            }
        }
    }

    data class EpisodeListItemEpoxyModel(
        val episode : Episode,
        val onClick : (Int) -> Unit
    ) : ViewBindingKotlinModel<ModelEpisodeListItemBinding>(R.layout.model_episode_list_item){
        override fun ModelEpisodeListItemBinding.bind() {
            episodeNumberTextView.text = episode.getFormattedSeasonTruncated()
            episodeNameTextView.text = episode.name
            episodeAirDateTextView.text = episode.air_date

            root.setOnClickListener{onClick(episode.id)}
        }
    }

    data class EpisodeListTitleEpoxyModel(
        val title : String
    ) : ViewBindingKotlinModel<ModelEpisodeListTitleBinding>(R.layout.model_episode_list_title){
        override fun ModelEpisodeListTitleBinding.bind() {
            titleTextView.text = title
        }
    }
}