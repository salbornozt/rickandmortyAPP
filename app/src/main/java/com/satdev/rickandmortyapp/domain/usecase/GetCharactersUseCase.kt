package com.satdev.rickandmortyapp.domain.usecase

import com.satdev.rickandmortyapp.data.model.Character
import com.satdev.rickandmortyapp.data.util.Resource
import com.satdev.rickandmortyapp.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val characterRepository: CharacterRepository) {

    suspend fun execute(page:String):Resource<ArrayList<Character>?> = characterRepository.getCharacters(page)

}