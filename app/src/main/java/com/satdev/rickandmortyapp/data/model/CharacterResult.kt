package com.satdev.rickandmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class CharacterResult(
    @SerializedName("info")
    val information: Information,
    @SerializedName("results")
    val characters: List<Character>
) {

}