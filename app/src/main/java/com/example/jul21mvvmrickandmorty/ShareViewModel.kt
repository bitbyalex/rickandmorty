package com.example.jul21mvvmrickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ShareViewModel : ViewModel() {
    private val repository = SharedRepository()

    private val _characterByIdLiveData = MutableLiveData<GetCharacterByIdResponse?>()
    val characterByIdResponseLiveData : LiveData<GetCharacterByIdResponse?> = _characterByIdLiveData

    fun refreshByCharacter(characterById: Int){
        viewModelScope.launch {
            val response = repository.getCharacterById(characterById)

            _characterByIdLiveData.postValue(response)
        }
    }

}