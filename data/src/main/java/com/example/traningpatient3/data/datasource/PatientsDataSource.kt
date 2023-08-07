package com.example.traningpatient3.data.datasource

import com.example.traningpatient3.domain.model.patients.PatientsWrappedRemoteModel
import retrofit2.http.GET

interface PatientsDataSource {

    @GET("patients")
    suspend fun getPatients(): PatientsWrappedRemoteModel
}