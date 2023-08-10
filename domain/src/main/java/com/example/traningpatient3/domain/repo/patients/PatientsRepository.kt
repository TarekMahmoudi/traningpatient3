package com.example.traningpatient3.domain.repo.patients

import com.example.traningpatient3.domain.model.add.AddPatientRemoteModel
import com.example.traningpatient3.domain.model.add.BodyAddPatientModel
import com.example.traningpatient3.domain.model.patients.PatientRemoteModel

interface PatientsRepository {

    suspend fun getPatients(): List<PatientRemoteModel>
    suspend fun addPatients( bodyAddPatientModel: BodyAddPatientModel): AddPatientRemoteModel

}