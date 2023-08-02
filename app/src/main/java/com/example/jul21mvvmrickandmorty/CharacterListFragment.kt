package com.example.jul21mvvmrickandmorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.jul21mvvmrickandmorty.characters.CharacterListEpoxyController
import com.example.jul21mvvmrickandmorty.characters.CharactersViewModel

class CharacterListFragment : Fragment() {

    private val epoxyController = CharacterListEpoxyController(::onCharacterSelected)
    private val viewModel : CharactersViewModel by lazy {
        ViewModelProvider(this)[CharactersViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.characterPagedListLiveData.observe(viewLifecycleOwner){pagedList ->
            epoxyController.submitList(pagedList)
        }

        view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView).setController(epoxyController)
    }

    private fun onCharacterSelected(characterId : Int){
        val direction = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailsFragment(characterId)
        findNavController().navigate(direction)
    }
}