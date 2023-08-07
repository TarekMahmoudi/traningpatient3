package com.example.traningpatient3.data.di

import com.example.traningpatient3.data.datasource.PatientsDataSource
import com.example.traningpatient3.data.repository.PatientsRepositoryImpl
import com.example.traningpatient3.domain.repo.patients.PatientsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepositoryPatients(patientsDataSource: PatientsDataSource): PatientsRepository {
        return PatientsRepositoryImpl(patientsDataSource)
    }

}