package com.example.jul21mvvmrickandmorty

import com.airbnb.epoxy.EpoxyController
import com.example.jul21mvvmrickandmorty.databinding.ModelCharacterDetailsDataPointBinding
import com.example.jul21mvvmrickandmorty.databinding.ModelCharacterDetailsHeaderBinding
import com.example.jul21mvvmrickandmorty.databinding.ModelCharacterDetailsImageBinding
import com.example.jul21mvvmrickandmorty.epoxy.LoadingEpoxyModel
import com.example.jul21mvvmrickandmorty.epoxy.ViewBindingKotlinModel
import com.example.jul21mvvmrickandmorty.network.response.GetCharacterByIdResponse
import com.squareup.picasso.Picasso

class CharacterDetailsEpoxyController : EpoxyController() {

    var isLoading : Boolean = true
        set(value){
            field = value
            if (field){
                requestModelBuild()
            }
        }

    var characterResponse : GetCharacterByIdResponse? = null
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

        if (characterResponse == null){
            // todo error state
            return
        }


        characterResponse?.let {
            // add header model
            HeaderEpoxyModel(
                name = it.name,
                gender = it.gender,
                status = it.status
            ).id("header").addTo(this)
            // add image model
            ImageEpoxyModel(
                image = it.image
            ).id("image").addTo(this)
            // add the data points model(s)
            // Origin
            DataPointEpoxyModel(
                title = "Origin",
                description = it.origin.name
            ).id("data_point_origin").addTo(this)
            // Species
            DataPointEpoxyModel(
                title = "Species",
                description = it.species
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

}