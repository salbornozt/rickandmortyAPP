package com.satdev.rickandmortyapp.data.api

import com.satdev.rickandmortyapp.data.model.CharacterResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientService {
    @GET("character")
    suspend fun getCharacters(@Query(
        "page"
    ) page: String):Response<CharacterResult>
}