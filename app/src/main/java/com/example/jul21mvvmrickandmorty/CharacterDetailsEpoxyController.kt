package com.example.jul21mvvmrickandmorty

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.example.jul21mvvmrickandmorty.databinding.ModelCharacterDetailsDataPointBinding
import com.example.jul21mvvmrickandmorty.databinding.ModelCharacterDetailsHeaderBinding
import com.example.jul21mvvmrickandmorty.databinding.ModelCharacterDetailsImageBinding
import com.example.jul21mvvmrickandmorty.databinding.ModelEpisodeCarouselItemBinding
import com.example.jul21mvvmrickandmorty.databinding.ModelTitleBinding
import com.example.jul21mvvmrickandmorty.domain.models.Character
import com.example.jul21mvvmrickandmorty.domain.models.Episode
import com.example.jul21mvvmrickandmorty.epoxy.LoadingEpoxyModel
import com.example.jul21mvvmrickandmorty.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class CharacterDetailsEpoxyController : EpoxyController() {

    private var isLoading : Boolean = true
        set(value){
            field = value
            if (field){
                requestModelBuild()
            }
        }

    var character : Character? = null
        set(value) {
            field = value
            if (field != null){
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {
        if (isLoading){
            //show loading state
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (character == null){
            // todo error state
            return
        }


        character?.let { character ->
            // add header model
            HeaderEpoxyModel(
                name = character.name,
                gender = character.gender,
                status = character.status
            ).id("header").addTo(this)
            // add image model
            ImageEpoxyModel(
                image = character.image
            ).id("image").addTo(this)

            if (character.episodeList.isNotEmpty()){
                val items = this.character!!.episodeList.map { episode ->
                    EpisodeCarouselItemEpoxyModel(episode).id(episode.id)
                }
                TitleEpoxyModel("Episodes").id("episode_title").addTo(this)
                CarouselModel_()
                    .models(items)
                    .numViewsToShowOnScreen(1.15f)
                    .id("episode_carousel")
                    .addTo(this)
            }

            // add the data points model(s)
            // Origin
            DataPointEpoxyModel(
                title = "Origin",
                description = character.origin.name
            ).id("data_point_origin").addTo(this)
            // Species
            DataPointEpoxyModel(
                title = "Species",
                description = character.species
            ).id("data_point_species").addTo(this)
        }

    }

    data class HeaderEpoxyModel(
        val name : String,
        val gender : String,
        val status : String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header){

        override fun ModelCharacterDetailsHeaderBinding.bind() {
            nameTextView.text = name
            statusTextView.text = status

            if(gender.equals("male",true)){
                genderImageView.setImageResource(R.drawable.ic_male_24)
            } else {
                genderImageView.setImageResource(R.drawable.ic_female_24)
            }
        }
    }

    data class ImageEpoxyModel(
        val image : String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsImageBinding>(R.layout.model_character_details_image){

        override fun ModelCharacterDetailsImageBinding.bind() {
            Picasso.get().load(image).into(imageView)
        }
    }

    data class DataPointEpoxyModel(
        val title : String,
        val description : String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point){

        override fun ModelCharacterDetailsDataPointBinding.bind() {
            labelTextView.text = title
            textView.text = description
        }
    }

    data class TitleEpoxyModel(
        val title: String
    ) : ViewBindingKotlinModel<ModelTitleBinding>(R.layout.model_title){
        override fun ModelTitleBinding.bind() {
            titleTextView.text = title
        }
    }

    data class EpisodeCarouselItemEpoxyModel(
        val episode: Episode
    ) : ViewBindingKotlinModel<ModelEpisodeCarouselItemBinding>(R.layout.model_episode_carousel_item) {

        override fun ModelEpisodeCarouselItemBinding.bind() {
            episodeTextView.text = episode.episode
            episodeDetailsTextView.text = "${episode.name}\n${episode.air_date}"
        }
    }

}