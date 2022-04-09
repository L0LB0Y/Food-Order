package com.example.foodorder.di

import com.example.foodorder.api.NetworkRequest
import com.example.foodorder.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SecondaryDependency {
    @Provides
    @ViewModelScoped
    fun provideHomeRepositoryInstance(networkRequest: NetworkRequest) =
        HomeRepository(networkRequest)

}