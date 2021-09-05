package com.satdev.rickandmortyapp.data.api

import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.data.model.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientService {
    @GET("character")
    suspend fun getCharacters():Response<CharacterList>
}