package com.satdev.rickandmortyapp.data.repository.dataSourceImpl

import com.satdev.rickandmortyapp.data.api.ClientService
import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.data.model.CharacterResult
import com.satdev.rickandmortyapp.data.repository.dataSource.CharacterRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(private val clientService: ClientService) : CharacterRemoteDataSource {
    override suspend fun getCharacters(page: String): Response<CharacterResult> {
        return clientService.getCharacters(page)
    }

    override suspend fun getCharacterDetail(characterId: Int): Response<Character?> {
        return clientService.getCharacterDetail(characterId)
    }
}