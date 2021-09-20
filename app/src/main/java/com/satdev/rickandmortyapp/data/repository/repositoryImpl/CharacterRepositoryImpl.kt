package com.satdev.rickandmortyapp.data.repository.repositoryImpl

import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.data.model.CharacterResult
import com.satdev.rickandmortyapp.data.repository.dataSource.CharacterRemoteDataSource
import com.satdev.rickandmortyapp.data.util.Resource
import com.satdev.rickandmortyapp.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val characterRemoteDataSource: CharacterRemoteDataSource): CharacterRepository {
    override suspend fun getCharacters(page: String): Resource<CharacterResult?> {
        var characterPage : CharacterResult ?= null
        var rta : Resource<CharacterResult?> = Resource.Loading()
        try {
            val response = characterRemoteDataSource.getCharacters(page)
            val body = response.body()
            if (body != null){
                characterPage = body
                rta = Resource.Success(characterPage)
            }else{
                rta = Resource.Success(null)
            }
        }catch (e:Exception){
             rta= Resource.Error(e.message!!,e)
        }
        return rta
    }
}