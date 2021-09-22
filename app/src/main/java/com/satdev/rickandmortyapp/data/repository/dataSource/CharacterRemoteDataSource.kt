package com.satdev.rickandmortyapp.data.repository.dataSource

import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.data.model.CharacterResult
import retrofit2.Response
import retrofit2.http.Query

interface CharacterRemoteDataSource {
    suspend fun getCharacters(page: String): Response<CharacterResult>

    suspend fun getCharacterDetail(characterId:Int):Response<Character?>
}