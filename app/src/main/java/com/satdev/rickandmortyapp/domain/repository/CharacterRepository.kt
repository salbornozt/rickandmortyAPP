package com.satdev.rickandmortyapp.domain.repository

import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.data.model.CharacterResult
import com.satdev.rickandmortyapp.data.util.Resource

interface CharacterRepository {
    suspend fun getCharacters(page:String):Resource<CharacterResult?>

    suspend fun getCharacterDetail(characterId:Int):Resource<Character?>
}