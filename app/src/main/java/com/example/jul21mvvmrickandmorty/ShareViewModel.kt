package com.example.jul21mvvmrickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jul21mvvmrickandmorty.domain.models.Character
import com.example.jul21mvvmrickandmorty.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.launch

class ShareViewModel : ViewModel() {
    private val repository = SharedRepository()

    private val _characterByIdLiveData = MutableLiveData<Character?>()
    val characterByIdResponseLiveData : LiveData<Character?> = _characterByIdLiveData

    fun refreshByCharacter(characterById: Int){
        viewModelScope.launch {
            val response = repository.getCharacterById(characterById)

            _characterByIdLiveData.postValue(response)
        }
    }

}