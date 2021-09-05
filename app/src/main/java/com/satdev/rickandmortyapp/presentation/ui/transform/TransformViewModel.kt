package com.satdev.rickandmortyapp.presentation.ui.transform

import android.app.Application
import androidx.lifecycle.*
import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.data.util.Resource
import com.satdev.rickandmortyapp.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransformViewModel @Inject constructor(application: Application,private val getCharactersUseCase: GetCharactersUseCase) : AndroidViewModel(application) {

    private val _texts = MutableLiveData<List<String>>().apply {
        value = (1..16).mapIndexed { _, i ->
            "This is item # $i"
        }
    }

    val texts: LiveData<List<String>> = _texts
    fun getCharacters() = liveData<Resource<ArrayList<Character>>>{
        val result = getCharactersUseCase.execute()
        when(result){
            is Resource.Loading->{
                println("loading")
            }
            is Resource.Success->{
                println("Succes")
                val list = result.data!!
                println(list)
            }
            is Resource.Error->{
                println("Error ${result.message}")
            }
        }


    }
}