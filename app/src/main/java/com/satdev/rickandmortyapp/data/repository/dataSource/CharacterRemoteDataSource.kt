package com.satdev.rickandmortyapp.data.repository.dataSource

import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.data.model.CharacterList
import retrofit2.Response

interface CharacterRemoteDataSource {
    suspend fun getCharacters(): Response<CharacterList>
}