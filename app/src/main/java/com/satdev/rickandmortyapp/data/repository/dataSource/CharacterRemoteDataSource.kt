package com.satdev.rickandmortyapp.data.repository.dataSource

import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.data.model.CharacterList
import retrofit2.Response
import retrofit2.http.Query

interface CharacterRemoteDataSource {
    suspend fun getCharacters(@Query(
        "page"
    ) page: String): Response<CharacterList>
}