package com.satdev.rickandmortyapp.presentation.ui.character.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.data.model.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val state: SavedStateHandle,
    application: Application
) : AndroidViewModel(application) {

    val character  = state.get<Character>("character")

    var name = state.get<String>("name") ?: character?.name ?: ""
        set(value) {
            field = value
            state.set("name",value)
        }

    var created = state.get<String>("created") ?: character?.created ?: ""
        set(value) {
            field = value
            state.set("created",value)
        }

    var gender = state.get<String>("gender") ?: character?.gender ?: ""
        set(value) {
            field = value
            state.set("gender",value)
        }

    var image = state.get<String>("image") ?: character?.image ?: ""
        set(value) {
            field = value
            state.set("image",value)
        }

    var location = state.get<Location>("location") ?: character?.location ?: Location("","")
        set(value) {
            field = value
            state.set("location",value)
        }




}