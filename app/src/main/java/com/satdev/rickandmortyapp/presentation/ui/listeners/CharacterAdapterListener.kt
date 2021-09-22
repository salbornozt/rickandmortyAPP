package com.satdev.rickandmortyapp.presentation.ui.listeners

import com.satdev.rickandmortyapp.data.model.Character

interface CharacterAdapterListener {
    fun getItemSelected(position:Int?=-1,character:Character?=null)
}