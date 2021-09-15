package com.satdev.rickandmortyapp.presentation.ui.transform

import android.app.Application
import androidx.lifecycle.*
import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.data.util.Resource
import com.satdev.rickandmortyapp.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TransformViewModel @Inject constructor(application: Application,private val getCharactersUseCase: GetCharactersUseCase) : AndroidViewModel(application) {

    private val _characterList :MutableLiveData<ArrayList<Character>> by lazy {
        MutableLiveData<ArrayList<Character>>().apply {
            getCharacters()
        }
    }

    val texts: LiveData<ArrayList<Character>> = _characterList
    fun getCharacters() = viewModelScope.launch{
        withContext(Dispatchers.IO){
            val result = getCharactersUseCase.execute("2")
            when(result){
                is Resource.Loading->{
                    println("loading")
                }
                is Resource.Success->{
                    println("Succes")
                    _characterList.postValue(result.data!!)
                    println(result.data!!.size)
                }
                is Resource.Error->{
                    println("Error ${result.message}")
                }
            }
        }



    }
}