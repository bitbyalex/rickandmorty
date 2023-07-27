package com.example.jul21mvvmrickandmorty.characters

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.jul21mvvmrickandmorty.R
import com.example.jul21mvvmrickandmorty.databinding.ModelCharacterListItemBinding
import com.example.jul21mvvmrickandmorty.epoxy.ViewBindingKotlinModel
import com.example.jul21mvvmrickandmorty.network.response.GetCharacterByIdResponse
import com.squareup.picasso.Picasso

class CharacterListEpoxyController : PagedListEpoxyController<GetCharacterByIdResponse>() {

    override fun buildItemModel(
        currentPosition: Int,
        item: GetCharacterByIdResponse?
    ): EpoxyModel<*> {
        return CharacterGridItemEpoxyModel(item!!.image , item.name).id(item.id)
    }

    data class CharacterGridItemEpoxyModel(
        val url : String,
        val name: String
    ) : ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item){
        override fun ModelCharacterListItemBinding.bind() {
            Picasso.get().load(url).into(characterImageView)
            characterNameTextView.text = name
        }

    }
}