package com.example.jul21mvvmrickandmorty.characters

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.jul21mvvmrickandmorty.R
import com.example.jul21mvvmrickandmorty.databinding.ModelCharacterListItemBinding
import com.example.jul21mvvmrickandmorty.databinding.ModelCharacterListTitleBinding
import com.example.jul21mvvmrickandmorty.epoxy.LoadingEpoxyModel
import com.example.jul21mvvmrickandmorty.epoxy.ViewBindingKotlinModel
import com.example.jul21mvvmrickandmorty.network.response.GetCharacterByIdResponse
import com.squareup.picasso.Picasso
import java.util.Locale

class CharacterListEpoxyController(
    private val onCharacterSelected: (Int) -> Unit
) : PagedListEpoxyController<GetCharacterByIdResponse>() {

    override fun buildItemModel(
        currentPosition: Int,
        item: GetCharacterByIdResponse?
    ): EpoxyModel<*> {
        return CharacterGridItemEpoxyModel(item!!.id, item.image , item.name, onCharacterSelected ).id(item.id)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {

        if (models.isEmpty()){
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        CharacterGridItemTitle("Main Family")
            .id("main_family_members")
            .addTo(this)

        super.addModels(models.subList(0,5))

        (models.subList(5,models.size) as List<CharacterGridItemEpoxyModel>).groupBy {
            it.name[0].uppercase()
        }.forEach{ mapEntry ->
            val character = mapEntry.key.uppercase(Locale.US)
            CharacterGridItemTitle(character)
                .id(character)
                .addTo(this)

            super.addModels(mapEntry.value)
        }
    }

    data class CharacterGridItemEpoxyModel(
        val characterId : Int,
        val url : String,
        val name: String,
        val onCharacterSelected: (Int) -> Unit
    ) : ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item){
        override fun ModelCharacterListItemBinding.bind() {
            Picasso.get().load(url).into(characterImageView)
            characterNameTextView.text = name

            root.setOnClickListener {
                onCharacterSelected(characterId)
            }
        }
    }

    data class CharacterGridItemTitle(
        val title : String
    ) : ViewBindingKotlinModel<ModelCharacterListTitleBinding>(R.layout.model_character_list_title){
        override fun ModelCharacterListTitleBinding.bind() {
            titleTextView.text = title
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }
    }
}