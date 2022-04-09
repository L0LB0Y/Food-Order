package com.example.foodorder.di

import android.content.Context
import com.example.foodorder.api.NetworkRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainDependency {

    // Provide Context Instance
    @Singleton
    @Provides
    fun providesContextInstance(@ApplicationContext context: Context) = context

    // Provide KTOR Instance
    @Singleton
    @Provides
    fun providesKTORInstance() = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        engine {
            connectTimeout = 40000
            socketTimeout = 40000
        }
    }

    // Provide Network Request Instance
    @Singleton
    @Provides
    fun provideNetworkRequestInstance(httpClient: HttpClient) =
        NetworkRequest(httpClient = httpClient)
}