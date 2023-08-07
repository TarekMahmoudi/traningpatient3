package com.example.traningpatient3.domain.usecase.patients

import com.example.traningpatient3.domain.model.patients.PatientRemoteModel
import com.example.traningpatient3.domain.repo.patients.PatientsRepository
import javax.inject.Inject

class GetPatientsUseCase @Inject constructor(private val repository: PatientsRepository) {

    suspend operator fun invoke(): List<PatientRemoteModel> {
        return repository.getPatients().sortedBy { it.namePatient }
    }
}