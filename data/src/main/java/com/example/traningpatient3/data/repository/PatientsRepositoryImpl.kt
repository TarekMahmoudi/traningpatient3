package com.example.traningpatient3.data.repository

import com.example.traningpatient3.data.datasource.PatientsDataSource
import com.example.traningpatient3.domain.model.patients.PatientRemoteModel
import com.example.traningpatient3.domain.repo.patients.PatientsRepository
import javax.inject.Inject


class PatientsRepositoryImpl @Inject constructor(private val patientsDataSource: PatientsDataSource) :
    PatientsRepository {

    override suspend fun getPatients(): List<PatientRemoteModel> {
        return patientsDataSource.getPatients().data
    }

}