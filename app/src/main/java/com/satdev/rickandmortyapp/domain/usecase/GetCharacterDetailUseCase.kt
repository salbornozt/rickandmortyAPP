package com.satdev.rickandmortyapp.domain.usecase

import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.data.util.Resource
import com.satdev.rickandmortyapp.domain.repository.CharacterRepository
import retrofit2.Response
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(private val characterRepository: CharacterRepository) {

    suspend fun execute(characterId:Int):Resource<Character?> = characterRepository.getCharacterDetail(characterId)
}