package com.zinoview.tzfilmsapp.presentation.di.module

import com.zinoview.tzfilmsapp.data.cloud.CloudDataSource
import com.zinoview.tzfilmsapp.data.cloud.CloudFilm
import com.zinoview.tzfilmsapp.data.cloud.FilmsService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    fun provideGson() = GsonConverterFactory.create()

    private companion object {
        private const val BASE_URL = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/"
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient,gsonConverterFactory: GsonConverterFactory) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideCloudUserService(retrofit: Retrofit) : FilmsService {
        return retrofit.create(FilmsService::class.java)
    }

    @Provides
    fun provideBaseCloudDataSource(service: FilmsService) : CloudDataSource<List<CloudFilm>> {
        return CloudDataSource.Base(service)
    }
}