package com.example.jul21mvvmrickandmorty.characters

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.jul21mvvmrickandmorty.Constants
import com.example.jul21mvvmrickandmorty.CharacterDetailsActivity
import com.example.jul21mvvmrickandmorty.R

class CharacterListActivity : AppCompatActivity() {

    private val epoxyController = CharacterListEpoxyController(::onCharacterSelected)
    private val viewModel : CharactersViewModel by lazy {
        ViewModelProvider(this)[CharactersViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        viewModel.characterPagedListLiveData.observe(this){pagedList ->
            epoxyController.submitList(pagedList)
        }

        findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView).setController(epoxyController)
    }

    private fun onCharacterSelected(characterId : Int){
        val intent = Intent(this, CharacterDetailsActivity::class.java)
        intent.putExtra(Constants.INTENT_EXTRA_CHARACTER_ID, characterId)
        startActivity(intent)
    }
}