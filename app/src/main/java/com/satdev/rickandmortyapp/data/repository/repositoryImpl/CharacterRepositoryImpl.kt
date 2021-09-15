package com.satdev.rickandmortyapp.data.repository.repositoryImpl

import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.data.repository.dataSource.CharacterRemoteDataSource
import com.satdev.rickandmortyapp.data.util.Resource
import com.satdev.rickandmortyapp.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val characterRemoteDataSource: CharacterRemoteDataSource): CharacterRepository {
    override suspend fun getCharacters(page: String): Resource<ArrayList<Character>?> {
        var characterList : ArrayList<Character> ?= null
        var rta : Resource<ArrayList<Character>?> = Resource.Loading()
        try {
            val response = characterRemoteDataSource.getCharacters(page)
            val body = response.body()
            if (body != null){
                characterList = ArrayList(body.characters)
                rta = Resource.Success(characterList)
            }else{
                rta = Resource.Success(null)
            }
        }catch (e:Exception){
             rta= Resource.Error(e.message!!)
        }
        return rta
    }
}