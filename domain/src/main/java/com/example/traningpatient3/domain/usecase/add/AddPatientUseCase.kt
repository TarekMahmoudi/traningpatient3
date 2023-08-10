package com.example.traningpatient3.domain.usecase.add

import com.example.traningpatient3.domain.model.add.AddPatientRemoteModel
import com.example.traningpatient3.domain.model.add.BodyAddPatientModel
import com.example.traningpatient3.domain.repo.patients.PatientsRepository
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repository: PatientsRepository) {

    suspend operator fun invoke(bodyAddPatientModel: BodyAddPatientModel) : AddPatientRemoteModel {
        return repository.addPatients(bodyAddPatientModel)
    }
}