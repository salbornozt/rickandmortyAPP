package com.satdev.rickandmortyapp.presentation.di

import com.satdev.rickandmortyapp.data.api.ClientService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rickandmortyapi.com/api/")
            .build()
    }

    @Singleton
    @Provides
    fun providesClientService(retrofit: Retrofit):ClientService{
        return retrofit.create(ClientService::class.java)
    }


}