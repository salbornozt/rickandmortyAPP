package com.satdev.rickandmortyapp.data.repository.dataSourceImpl

import com.satdev.rickandmortyapp.data.api.ClientService
import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.data.model.CharacterList
import com.satdev.rickandmortyapp.data.repository.dataSource.CharacterRemoteDataSource
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(private val clientService: ClientService) : CharacterRemoteDataSource {
    override suspend fun getCharacters(page: String): Response<CharacterList> {
        return clientService.getCharacters(page)
    }
}