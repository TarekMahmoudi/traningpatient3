package com.example.traningpatient3.data.di

import com.example.traningpatient3.data.datasource.PatientsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
     const val baseUrl = "https://patients-app-api.herokuapp.com/"
@Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun providePatientsDataSource(retrofit: Retrofit): PatientsDataSource {
        return retrofit.create(PatientsDataSource::class.java)

    }

}