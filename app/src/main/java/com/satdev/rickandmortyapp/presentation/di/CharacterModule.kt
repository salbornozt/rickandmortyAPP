package com.satdev.rickandmortyapp.presentation.di

import com.satdev.rickandmortyapp.data.api.ClientService
import com.satdev.rickandmortyapp.data.repository.dataSource.CharacterRemoteDataSource
import com.satdev.rickandmortyapp.data.repository.dataSourceImpl.CharacterRemoteDataSourceImpl
import com.satdev.rickandmortyapp.data.repository.repositoryImpl.CharacterRepositoryImpl
import com.satdev.rickandmortyapp.domain.repository.CharacterRepository
import com.satdev.rickandmortyapp.domain.usecase.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterModule {
    @Provides
    @Singleton
    fun providesCharacterRepository(characterRemoteDataSource: CharacterRemoteDataSource):CharacterRepository = CharacterRepositoryImpl(characterRemoteDataSource)

    @Provides
    @Singleton
    fun providesCharacterRemoteDatasource(clientService: ClientService):CharacterRemoteDataSource = CharacterRemoteDataSourceImpl(clientService)

    @Provides
    @Singleton
    fun providesGetCharactersUseCase(characterRepository: CharacterRepository) : GetCharactersUseCase = GetCharactersUseCase(characterRepository)


}