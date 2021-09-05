package com.satdev.rickandmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class CharacterList(
    @SerializedName("results")
    val characters: List<Character>
) {

}